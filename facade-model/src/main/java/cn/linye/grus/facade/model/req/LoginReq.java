package cn.linye.grus.facade.model.req;

/**
 * @Author tianzhonghai
 * @Date 2017/7/17.
 */
public class LoginReq {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
