package cn.linye.grus.facade.model.admin.req;

import cn.linye.grus.facade.model.BaseReq;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

/**
 * @Author tianzhonghai
 * @Date 2017/7/17.
 */
public class LoginReq extends BaseReq {
    @NotBlank(message = "登录名不能为空")
    @Size(min = 3, max = 16)
    private String account;

    @NotBlank(message = "登录密码不能为空")
    @Size(min = 6, max = 16)
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
