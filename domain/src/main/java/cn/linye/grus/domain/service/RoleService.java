package cn.linye.grus.domain.service;

import cn.linye.grus.domain.dtos.RoleDto;
import cn.linye.grus.facade.model.admin.req.QueryRolesReq;
import cn.linye.grus.infrastructure.PagedCollection;

/**
 * 角色服务
 * @Author tianzhonghai
 * @Date 2017/7/27.
 */
public interface RoleService {
    PagedCollection<RoleDto> queryRoleList(QueryRolesReq queryRolesReq);
}
