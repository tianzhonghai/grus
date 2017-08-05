package cn.linye.grus.facade.model.admin.req;

import cn.linye.grus.infrastructure.BasePagedReq;
import cn.linye.grus.infrastructure.BaseReq;

/**
 * Created by Tim on 2017/8/5.
 */
public class QueryDeptsReq extends BasePagedReq {
    private String deptName;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
