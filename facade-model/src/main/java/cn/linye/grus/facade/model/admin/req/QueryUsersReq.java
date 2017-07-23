package cn.linye.grus.facade.model.admin.req;

import cn.linye.grus.facade.model.BasePagedReq;

import java.util.Date;

/**
 * 查询用户
 * Created by Tim on 2017/7/22.
 */
public class QueryUsersReq extends BasePagedReq {
    private String account;
    private String username;


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
