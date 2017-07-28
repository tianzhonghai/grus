package cn.linye.grus.domain.dtos.resp;

/**
 * Created by Tim on 2017/7/26.
 */
public class PermissionWithCheckedRespDto {
    private Integer permissionid;
    private String permissionname;
    private Integer parentpermissionid;
    private Integer checked;

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

    public Integer getParentpermissionid() {
        return parentpermissionid;
    }

    public void setParentpermissionid(Integer parentpermissionid) {
        this.parentpermissionid = parentpermissionid;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }
}
