package cn.linye.grus.domain.service;

import cn.linye.grus.domain.dtos.common.DeptDto;
import cn.linye.grus.facade.model.admin.req.AddDeptReq;
import cn.linye.grus.facade.model.admin.req.QueryDeptsReq;
import cn.linye.grus.infrastructure.PagedCollection;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Tim on 2017/8/4.
 */
public interface DeptService {
    PagedCollection<DeptDto> queryDeptList(QueryDeptsReq queryDeptsReq);

    int updateDeptEnabled(int deptId, boolean enabled);

    void addDept(AddDeptReq addDeptReq);

    DeptDto getDept(int deptId);

    void editDept(AddDeptReq addDeptReq);
}
