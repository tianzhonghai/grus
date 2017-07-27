package cn.linye.grus.facade.model.admin.req;

import cn.linye.grus.infrastructure.BasePagedReq;

/**
 * @Author tianzhonghai
 * @Date 2017/7/27.
 */
public class QueryRolesReq extends BasePagedReq {
    private String rolename;

    public String getRolename() {
        return rolename;
    }

    public QueryRolesReq setRolename(String rolename) {
        this.rolename = rolename;
        return this;
    }
}
