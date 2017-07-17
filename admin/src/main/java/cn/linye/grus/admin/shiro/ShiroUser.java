package cn.linye.grus.admin.shiro;

/**
 * Created by Tim on 2017/7/17.
 */
public class ShiroUser {
    private int userId;
    private String account;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}