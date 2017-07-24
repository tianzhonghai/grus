package cn.linye.grus.domain.service;

import cn.linye.grus.domain.entity.UserWithProfileEntity;
import cn.linye.grus.domain.entity.generated.UserEntity;
import cn.linye.grus.domain.entity.generated.UserEntityExample;
import cn.linye.grus.domain.entity.generated.UserProfileEntity;
import cn.linye.grus.domain.repository.UserRepository;
import cn.linye.grus.domain.repository.generated.UserMapper;
import cn.linye.grus.domain.repository.generated.UserProfileMapper;
import cn.linye.grus.facade.model.PagedCollectionResp;
import cn.linye.grus.facade.model.admin.req.AddUserReq;
import cn.linye.grus.facade.model.admin.req.QueryUsersReq;
import cn.linye.grus.facade.model.admin.resp.QueryUsersResp;
import cn.linye.grus.infrastructure.exception.BizException;
import cn.linye.grus.infrastructure.utils.DozerUtils;
import cn.linye.grus.infrastructure.utils.SecretUtils;
import cn.linye.grus.infrastructure.utils.SpringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.dozer.Mapper;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Tim on 2017/7/16.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Autowired
    private UserRepository userRepository;

    public UserEntity getUserEntityByAccount(String account) {
        UserEntityExample userPOExample = new UserEntityExample();
        userPOExample.createCriteria().andAccountEqualTo(account);
        List<UserEntity> entities = userMapper.selectByExample(userPOExample);

        if (entities != null && entities.size() > 0) {
            return entities.get(0);
        }
        return null;
    }

    public UserEntity getUserEntityByUserId(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public PagedCollectionResp<QueryUsersResp> queryUserList(QueryUsersReq queryUserReq) {
        List<UserWithProfileEntity> userWithProfileEntities = userRepository.queryUserWithProfileEntities(queryUserReq.getAccount(), queryUserReq.getUsername(), queryUserReq.getStart(), queryUserReq.getLength());
        int count = userRepository.countUserWithProfileEntities(queryUserReq.getAccount(), queryUserReq.getUsername());
        PagedCollectionResp<QueryUsersResp> result = new PagedCollectionResp<>();
        List<QueryUsersResp> list = new ArrayList<>();
        for (UserWithProfileEntity item :
                userWithProfileEntities) {
            //org.dozer.Mapper mapper = getMapper();
            QueryUsersResp q = DozerUtils.getDozerMapper().map(item, QueryUsersResp.class);
            list.add(q);
        }
        result.setDraw(queryUserReq.getDraw());
        result.setRecordsFiltered(count);
        result.setData(list);
        result.setRecordsTotal(count);
        return result;
    }

    public void lockUser(int userId, boolean locked) {
        userRepository.updateUserLocked(userId, locked);
    }

    public void enableUser(int userId, boolean enabled) {
        userRepository.updateUserEnabled(userId, enabled);
    }

    public void addUser(AddUserReq req) {
        SqlSessionFactoryBean sqlSessionFactoryBean = SpringUtils.getBean(SqlSessionFactoryBean.class);
        SqlSessionFactory sqlSessionFactory = null;
        try {
            sqlSessionFactory = sqlSessionFactoryBean.getObject();
        } catch (Exception ex) {
            BizException.throwFail(ex.getMessage(),ex);
        }
        UserEntity userEntity = DozerUtils.getDozerMapper().map(req, UserEntity.class);
        userEntity.setCreatedtime(new Date());
        userEntity.setLocked(false);
        userEntity.setEnabled(true);
        userEntity.setPassword(SecretUtils.MD5("111111"));
        UserProfileEntity userProfileEntity = DozerUtils.getDozerMapper().map(req, UserProfileEntity.class);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            int userId = userMapper.insert(userEntity);
            userProfileEntity.setUserid(userId);
            userProfileMapper.insert(userProfileEntity);
            sqlSession.commit();
        } catch (Exception ex) {
            sqlSession.rollback();
            BizException.throwFail(ex.getMessage(),ex);
        }
    }


}
