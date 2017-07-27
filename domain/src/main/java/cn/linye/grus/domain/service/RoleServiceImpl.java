package cn.linye.grus.domain.service;

import cn.linye.grus.domain.dtos.RoleDto;
import cn.linye.grus.domain.entity.generated.RoleEntity;
import cn.linye.grus.domain.entity.generated.RoleEntityExample;
import cn.linye.grus.domain.repository.generated.RoleMapper;
import cn.linye.grus.facade.model.admin.req.QueryRolesReq;
import cn.linye.grus.infrastructure.PagedCollection;
import cn.linye.grus.infrastructure.utils.DozerUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author tianzhonghai
 * @Date 2017/7/27.
 */
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public PagedCollection<RoleDto> queryRoleList(QueryRolesReq queryRolesReq) {
        RoleEntityExample roleEntityExample = new RoleEntityExample();
        roleEntityExample.createCriteria().andRolenameLike(queryRolesReq.getRolename());

        List<RoleEntity> roleEntities = roleMapper.selectByExample(roleEntityExample);
        long count = roleMapper.countByExample(roleEntityExample);

        PagedCollection<RoleDto> pagedCollection = new PagedCollection<>();
        pagedCollection.setData(DozerUtils.mapList(roleEntities,RoleDto.class));
        pagedCollection.setRecordsTotal((int)count);
        pagedCollection.setDraw(queryRolesReq.getDraw());
        pagedCollection.setRecordsFiltered((int)count);

        return pagedCollection;
    }
}
