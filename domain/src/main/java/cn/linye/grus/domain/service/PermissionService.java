package cn.linye.grus.domain.service;

import cn.linye.grus.domain.dtos.PermissionRespDto;
import cn.linye.grus.domain.dtos.PermissionWithCheckedRespDto;

import java.util.List;

/**
 * Created by Tim on 2017/7/17.
 */
public interface PermissionService {
    List<PermissionRespDto> getUserPermissions(int userId);

    List<PermissionWithCheckedRespDto> getAllPermissionsWithChecked(int roleId);
}
