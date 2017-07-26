package cn.linye.grus.domain.service;

import cn.linye.grus.domain.dtos.PermissionRespDto;
import cn.linye.grus.domain.dtos.PermissionWithCheckedRespDto;
import cn.linye.grus.domain.entity.PermissionWithCheckedEntity;
import cn.linye.grus.domain.entity.generated.PermissionEntity;
import cn.linye.grus.domain.repository.PermissionRepository;
import cn.linye.grus.facade.model.admin.resp.GetAllPermissionsWithCheckedResp;
import cn.linye.grus.infrastructure.caching.GuavaCache;
import cn.linye.grus.infrastructure.utils.DozerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Tim on 2017/7/17.
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    private static final Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private GuavaCache guavaCache;

    public List<PermissionRespDto> getUserPermissions(int userId) {
        List<PermissionEntity> entities = new ArrayList<>();
        try {
            Object obj = guavaCache.getCache().get(Integer.toString(userId), () -> {
                return permissionRepository.getUserPermissions(userId);
            });
            if(obj != null) entities = (List<PermissionEntity>)obj;
        }catch (ExecutionException ex){
            logger.error(ex.getMessage(),ex);
        }
        return DozerUtils.getDozerMapper().map(entities , new ArrayList<PermissionRespDto>().getClass());
    }

    public List<PermissionWithCheckedRespDto> getAllPermissionsWithChecked(int roleId) {
        List<PermissionWithCheckedEntity> entities = permissionRepository.getAllPermissionsWithChecked(roleId);
        List<PermissionWithCheckedRespDto> dtos = DozerUtils.getDozerMapper().map(entities , new ArrayList<PermissionWithCheckedRespDto>().getClass());
        return dtos;
    }
}
