package cn.linye.grus.domain.entity;

/**
 * @Author tianzhonghai
 * @Date 2017/7/31.
 */
public class RoleWithCheckedEntity {
    private Integer roleid;
    private String rolename;
    private Integer checked;

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }
}
