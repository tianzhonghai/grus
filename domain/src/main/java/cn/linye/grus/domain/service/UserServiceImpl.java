package cn.linye.grus.domain.service;

import cn.linye.grus.domain.entity.UserWithProfileEntity;
import cn.linye.grus.domain.entity.generated.UserEntity;
import cn.linye.grus.domain.entity.generated.UserEntityExample;
import cn.linye.grus.domain.repository.UserRepository;
import cn.linye.grus.domain.repository.generated.UserMapper;
import cn.linye.grus.facade.model.PagedCollectionResp;
import cn.linye.grus.facade.model.admin.req.QueryUserReq;
import org.dozer.Mapper;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public UserEntity getUserEntityByAccount(String account) {
        UserEntityExample userPOExample = new UserEntityExample();
        userPOExample.createCriteria().andAccountEqualTo(account);
        List<UserEntity> entities = userMapper.selectByExample(userPOExample);

        if(entities != null && entities.size() > 0){
            return entities.get(0);
        }
        return null;
    }

    public PagedCollectionResp<UserEntity> queryUserList(QueryUserReq queryUserReq) {

        List<UserWithProfileEntity> userWithProfileEntities = userRepository.queryUserWithProfileEntities(null, "admin");

        UserEntityExample userEntityExample = new UserEntityExample();
        userEntityExample.createCriteria().andAccountLike(queryUserReq.getAccount());

        List<UserEntity> userEntities = userMapper.selectByExample(userEntityExample);
        long count = userMapper.countByExample(userEntityExample);

        PagedCollectionResp<UserEntity> result = new PagedCollectionResp<>();
        result.setData(userEntities);
        result.setRecordsTotal((int)count);
        return result;
    }


}
