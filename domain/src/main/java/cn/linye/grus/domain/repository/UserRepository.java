package cn.linye.grus.domain.repository;

import cn.linye.grus.domain.entity.UserWithProfileEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Tim on 2017/7/22.
 */
public interface UserRepository {
    List<UserWithProfileEntity> queryUserWithProfileEntities(@Param("account")String  account, @Param("userName") String userName, @Param("start")int start,@Param("limit") int limit);

    int countUserWithProfileEntities(@Param("account") String account, @Param("userName") String userName);

    int updateUserLocked(@Param("userId") int userId, @Param("locked") boolean locked);

    int updateUserEnabled(@Param("userId") int userId, @Param("enabled") boolean enabled);

    UserWithProfileEntity getUserWithProfileEntity(@Param("userid")int userId);
}
