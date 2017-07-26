package cn.linye.grus.domain.repository;

import cn.linye.grus.domain.entity.PermissionWithCheckedEntity;
import cn.linye.grus.domain.entity.generated.PermissionEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Tim on 2017/7/17.
 */
public interface PermissionRepository {
    /**
     * 获取用户权限
     * @param userId
     * @return
     */
    List<PermissionEntity> getUserPermissions(@Param("uesrId") int userId);

    /**
     * 获取所有权限并且根据角色判断checked
     * @param roleId
     * @return
     */
    List<PermissionWithCheckedEntity> getAllPermissionsWithChecked(@Param("roleId")int roleId);
}
