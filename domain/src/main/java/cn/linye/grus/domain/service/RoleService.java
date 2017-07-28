package cn.linye.grus.domain.service;

import cn.linye.grus.domain.dtos.RoleDto;
import cn.linye.grus.facade.model.admin.req.AddRoleReq;
import cn.linye.grus.facade.model.admin.req.QueryRolesReq;
import cn.linye.grus.infrastructure.PagedCollection;

/**
 * 角色服务
 * @Author tianzhonghai
 * @Date 2017/7/27.
 */
public interface RoleService {
    /**
     * 查询角色
     * @param queryRolesReq
     * @return
     */
    PagedCollection<RoleDto> queryRoleList(QueryRolesReq queryRolesReq);

    /**
     * 添加角色
     * @param addRoleReq
     */
    void  addRole(AddRoleReq addRoleReq);

    RoleDto getRoleById(int roleId);

    void editRole(AddRoleReq addRoleReq);

    void deleteRole(int roleId);
}
