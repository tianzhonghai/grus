package cn.linye.grus.facade.model.admin.req;

import cn.linye.grus.infrastructure.BaseReq;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * @Author tianzhonghai
 * @Date 2017/7/27.
 */
public class AddRoleReq extends BaseReq {
    @NotBlank
    private String rolename;
    private String roledesc;
    @NotEmpty
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
