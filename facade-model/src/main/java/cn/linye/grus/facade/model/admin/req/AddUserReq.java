package cn.linye.grus.facade.model.admin.req;

import cn.linye.grus.infrastructure.BaseReq;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;
import java.util.List;

/**
 * @Author tianzhonghai
 * @Date 2017/7/24.
 */
public class AddUserReq extends BaseReq {
    private Integer userid;
    @NotBlank
    private String account;
    private Boolean locked;
    private Boolean enabled;

    @NotBlank
    private String username;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @NotEmpty
    private List<Integer> roleids;

    public Integer getUserid() {
        return userid;
    }

    public AddUserReq setUserid(Integer userid) {
        this.userid = userid;
        return this;
    }

    public String getAccount() {
        return account;
    }

    public AddUserReq setAccount(String account) {
        this.account = account;
        return this;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public AddUserReq setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AddUserReq setUsername(String username) {
        this.username = username;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public AddUserReq setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public Boolean getLocked() {
        return locked;
    }

    public AddUserReq setLocked(Boolean locked) {
        this.locked = locked;
        return this;
    }

    public List<Integer> getRoleids() {
        return roleids;
    }

    public void setRoleids(List<Integer> roleids) {
        this.roleids = roleids;
    }
}
