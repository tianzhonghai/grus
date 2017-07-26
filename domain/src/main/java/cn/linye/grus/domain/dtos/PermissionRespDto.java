package cn.linye.grus.domain.dtos;

/**
 * Created by Tim on 2017/7/26.
 */
public class PermissionRespDto {
    private Integer permissionid;

    private String permissionname;

    private String description;

    private String url;

    private Boolean ismenu;

    private String permissioncode;

    private Integer parentpermissionid;

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
        this.permissionname = permissionname == null ? null : permissionname.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
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
        this.permissioncode = permissioncode == null ? null : permissioncode.trim();
    }

    public Integer getParentpermissionid() {
        return parentpermissionid;
    }

    public void setParentpermissionid(Integer parentpermissionid) {
        this.parentpermissionid = parentpermissionid;
    }
}
