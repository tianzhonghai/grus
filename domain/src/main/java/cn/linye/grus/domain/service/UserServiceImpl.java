package cn.linye.grus.domain.service;

import cn.linye.grus.domain.entity.UserWithProfileEntity;
import cn.linye.grus.domain.entity.generated.UserEntity;
import cn.linye.grus.domain.entity.generated.UserEntityExample;
import cn.linye.grus.domain.repository.UserRepository;
import cn.linye.grus.domain.repository.generated.UserMapper;
import cn.linye.grus.facade.model.PagedCollectionResp;
import cn.linye.grus.facade.model.admin.req.QueryUsersReq;
import cn.linye.grus.facade.model.admin.resp.QueryUsersResp;
import cn.linye.grus.infrastructure.utils.DozerUtils;
import org.dozer.Mapper;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim on 2017/7/16.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private DozerBeanMapperFactoryBean dozerBean;



    public UserEntity getUserEntityByAccount(String account) {
        UserEntityExample userPOExample = new UserEntityExample();
        userPOExample.createCriteria().andAccountEqualTo(account);
        List<UserEntity> entities = userMapper.selectByExample(userPOExample);

        if(entities != null && entities.size() > 0){
            return entities.get(0);
        }
        return null;
    }

    public PagedCollectionResp<QueryUsersResp> queryUserList(QueryUsersReq queryUserReq) {

        List<UserWithProfileEntity> userWithProfileEntities = userRepository.queryUserWithProfileEntities(queryUserReq.getAccount(), queryUserReq.getUsername());
        int count = userRepository.countUserWithProfileEntities(queryUserReq.getAccount(), queryUserReq.getUsername());

        PagedCollectionResp<QueryUsersResp> result = new PagedCollectionResp<>();
        List<QueryUsersResp> list = new ArrayList<>();
        for (UserWithProfileEntity item :
                userWithProfileEntities) {
            //org.dozer.Mapper mapper = getMapper();
            QueryUsersResp q = DozerUtils.getDozerMapper().map(item,QueryUsersResp.class);
            list.add(q);
        }
        result.setData(list);
        result.setRecordsTotal(count);
        return result;
    }


//    private Mapper getMapper() {
//        Mapper mapper = null;
//        try {
//            mapper = dozerBean.getObject();
//        }catch (Exception ex){
//            throw new RuntimeException("获取dozerMapper异常",ex);
//        }
//        return mapper;
//    }
}
