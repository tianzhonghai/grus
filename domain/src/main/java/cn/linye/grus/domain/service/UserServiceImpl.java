package cn.linye.grus.domain.service;

import cn.linye.grus.domain.entity.generated.UserEntity;
import cn.linye.grus.domain.entity.generated.UserEntityExample;
import cn.linye.grus.domain.repository.generated.UserMapper;
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

    public UserEntity getUserEntityByAccount(String account) {
        UserEntityExample userPOExample = new UserEntityExample();
        userPOExample.createCriteria().andAccountEqualTo(account);
        List<UserEntity> entities = userMapper.selectByExample(userPOExample);

        if(entities != null && entities.size() > 0){
            return entities.get(0);
        }
        return null;
    }
}
