package cn.linye.grus.facade.model.admin.req;

import cn.linye.grus.infrastructure.BaseReq;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Tim on 2017/8/5.
 */
public class AddDeptReq extends BaseReq {
    @NotBlank(message = "部门名称不能为空")
    private String deptName;

    private String createdby;
    private String lastmodifiedby;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getLastmodifiedby() {
        return lastmodifiedby;
    }

    public void setLastmodifiedby(String lastmodifiedby) {
        this.lastmodifiedby = lastmodifiedby;
    }
}
