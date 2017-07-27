package cn.linye.grus.facade.model.admin.req;

import cn.linye.grus.facade.model.BaseReq;

import java.util.List;

/**
 * @Author tianzhonghai
 * @Date 2017/7/27.
 */
public class AddRoleReq extends BaseReq {
    private String rolename;
    private String roledesc;
    private List<Integer> permissionIds;

    public String getRolename() {
        return rolename;
    }

    public AddRoleReq setRolename(String rolename) {
        this.rolename = rolename;
        return this;
    }

    public String getRoledesc() {
        return roledesc;
    }

    public AddRoleReq setRoledesc(String roledesc) {
        this.roledesc = roledesc;
        return this;
    }

    public List<Integer> getPermissionIds() {
        return permissionIds;
    }

    public AddRoleReq setPermissionIds(List<Integer> permissionIds) {
        this.permissionIds = permissionIds;
        return this;
    }
}
