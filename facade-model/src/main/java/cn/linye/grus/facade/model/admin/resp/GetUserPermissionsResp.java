package cn.linye.grus.facade.model.admin.resp;

/**
 * @Author tianzhonghai
 * @Date 2017/7/19.
 */
public class GetUserPermissionsResp {
    private Integer permissionid;
    private String permissionname;
    private String url;
    private Boolean ismenu;
    private String permissioncode;
    private Integer parentPermissionId;

    public Integer getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(Integer permissionid) {
        this.permissionid = permissionid;
    }

    public String getPermissionname() {
        return permissionname;
    }

    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getIsmenu() {
        return ismenu;
    }

    public void setIsmenu(Boolean ismenu) {
        this.ismenu = ismenu;
    }

    public String getPermissioncode() {
        return permissioncode;
    }

    public void setPermissioncode(String permissioncode) {
        this.permissioncode = permissioncode;
    }

    public Integer getParentPermissionId() {
        return parentPermissionId;
    }

    public void setParentPermissionId(Integer parentPermissionId) {
        this.parentPermissionId = parentPermissionId;
    }
}
