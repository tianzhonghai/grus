package cn.linye.grus.domain.service;

import cn.linye.grus.domain.dtos.resp.PermissionRespDto;
import cn.linye.grus.domain.dtos.resp.PermissionWithCheckedRespDto;
import cn.linye.grus.domain.entity.PermissionWithCheckedEntity;
import cn.linye.grus.domain.entity.generated.PermissionEntity;
import cn.linye.grus.domain.repository.PermissionRepository;
import cn.linye.grus.domain.repository.generated.PermissionMapper;
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
    private PermissionMapper permissionMapper;

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
        return DozerUtils.mapList(entities , PermissionRespDto.class);
    }

    public List<PermissionWithCheckedRespDto> getAllPermissionsWithChecked(int roleId) {
        List<PermissionWithCheckedRespDto> dtos = new ArrayList<>();
        if(roleId == 0){
            List<PermissionEntity> permissionEntities = permissionMapper.selectByExample(null);
            if(permissionEntities != null){
                for (PermissionEntity entity : permissionEntities) {
                    PermissionWithCheckedRespDto dto = DozerUtils.getDozerMapper().map(entity, PermissionWithCheckedRespDto.class);
                    dtos.add(dto);
                }
            }
        }
        else {
            List<PermissionWithCheckedEntity> entities = permissionRepository.getAllPermissionsWithChecked(roleId);
            if(entities != null) {
                for (PermissionWithCheckedEntity entity : entities) {
                    PermissionWithCheckedRespDto dto = DozerUtils.getDozerMapper().map(entity, PermissionWithCheckedRespDto.class);
                    dtos.add(dto);
                }
            }
        }
        return dtos;
    }
}
