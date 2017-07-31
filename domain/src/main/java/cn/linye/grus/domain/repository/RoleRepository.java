package cn.linye.grus.domain.repository;

import cn.linye.grus.domain.entity.RoleWithCheckedEntity;
import cn.linye.grus.domain.entity.generated.RoleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author tianzhonghai
 * @Date 2017/7/27.
 */
public interface RoleRepository {
    List<RoleEntity> queryRoleEntities(@Param("roleName") String roleName, @Param("start")int start, @Param("limit") int limit);

    int countRoleEntities(@Param("roleName") String roleName);

    List<RoleWithCheckedEntity> getRoleWithCheckedEntities(@Param("userId")Integer userId);
}
