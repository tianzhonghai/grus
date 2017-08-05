package cn.linye.grus.domain.service;

import cn.linye.grus.domain.dtos.common.DeptDto;
import cn.linye.grus.facade.model.admin.req.QueryDeptsReq;
import cn.linye.grus.infrastructure.PagedCollection;

/**
 * Created by Tim on 2017/8/4.
 */
public interface DeptService {
    PagedCollection<DeptDto> queryDeptList(QueryDeptsReq queryDeptsReq);
}
