package cn.linye.grus.facade.model.admin.resp;

import java.util.List;

/**
 * ztree model
 * Created by Tim on 2017/7/26.
 */
public class PermissionTreeNodeResp {
    private Integer id;
    private String name;
    private Integer pid;
    private boolean checked;
    private boolean open;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean getOpen() {
        return open;
    }

    public PermissionTreeNodeResp setOpen(boolean open) {
        this.open = open;
        return this;
    }
}
