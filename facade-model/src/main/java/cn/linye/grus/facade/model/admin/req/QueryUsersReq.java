package cn.linye.grus.facade.model.admin.req;

import java.util.Date;

/**
 * 查询用户
 * Created by Tim on 2017/7/22.
 */
public class QueryUsersReq {
    private Integer userid;
    private String account;
    private String username;
    private Date birthday;

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
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
