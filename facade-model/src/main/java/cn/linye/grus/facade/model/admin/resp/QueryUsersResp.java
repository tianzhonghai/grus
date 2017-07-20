package cn.linye.grus.facade.model.admin.resp;

import java.util.Date;

/**
 * Created by Tim on 2017/7/20.
 */
public class QueryUsersResp {
    private Integer userid;

    private String account;

    private Date createdtime;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }
}
