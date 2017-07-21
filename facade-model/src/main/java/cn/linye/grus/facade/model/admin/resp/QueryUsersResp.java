package cn.linye.grus.facade.model.admin.resp;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by Tim on 2017/7/20.
 */
public class QueryUsersResp {
    private Integer userid;

    private String account;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
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
