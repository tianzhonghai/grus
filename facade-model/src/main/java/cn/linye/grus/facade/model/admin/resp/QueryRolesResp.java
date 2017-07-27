package cn.linye.grus.facade.model.admin.resp;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @Author tianzhonghai
 * @Date 2017/7/27.
 */
public class QueryRolesResp {
    private Integer roleid;
    private String rolename;
    private String roledesc;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdtime;
    private Boolean issystem;

    public Boolean getIssystem() {
        return issystem;
    }

    public void setIssystem(Boolean issystem) {
        this.issystem = issystem;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public QueryRolesResp setRoleid(Integer roleid) {
        this.roleid = roleid;
        return this;
    }

    public String getRolename() {
        return rolename;
    }

    public QueryRolesResp setRolename(String rolename) {
        this.rolename = rolename;
        return this;
    }

    public String getRoledesc() {
        return roledesc;
    }

    public QueryRolesResp setRoledesc(String roledesc) {
        this.roledesc = roledesc;
        return this;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public QueryRolesResp setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
        return this;
    }
}
