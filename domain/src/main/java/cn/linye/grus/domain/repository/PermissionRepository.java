package cn.linye.grus.domain.repository;

import cn.linye.grus.domain.entity.generated.PermissionEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Tim on 2017/7/17.
 */
public interface PermissionRepository {
    List<PermissionEntity> getUserPermissions(@Param("uesrId") int userId);
}
