package cn.linye.grus.domain.service;

import cn.linye.grus.domain.dtos.common.DeptDto;
import cn.linye.grus.domain.repository.generated.DeptMapper;
import cn.linye.grus.infrastructure.BaseReq;
import cn.linye.grus.infrastructure.PagedCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Tim on 2017/8/4.
 */
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    public PagedCollection<DeptDto> queryDeptList(BaseReq baseReq) {
        return null;
    }
}
