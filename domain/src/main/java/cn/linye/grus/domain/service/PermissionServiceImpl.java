package cn.linye.grus.domain.service;

import cn.linye.grus.domain.entity.generated.PermissionEntity;
import cn.linye.grus.domain.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Tim on 2017/7/17.
 */
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public List<PermissionEntity> getUserPermissions(int userId) {
        return permissionRepository.getUserPermissions(userId);
    }
}
