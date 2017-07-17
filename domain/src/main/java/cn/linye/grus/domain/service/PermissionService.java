package cn.linye.grus.domain.service;

import cn.linye.grus.domain.entity.generated.PermissionEntity;

import java.util.List;

/**
 * Created by Tim on 2017/7/17.
 */
public interface PermissionService {
    List<PermissionEntity> getUserPermissions(int userId);
}
