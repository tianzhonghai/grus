package cn.linye.grus.domain.service;

import cn.linye.grus.domain.dtos.common.DeptDto;
import cn.linye.grus.domain.entity.generated.DeptEntity;
import cn.linye.grus.domain.repository.DeptRepository;
import cn.linye.grus.domain.repository.generated.DeptMapper;
import cn.linye.grus.facade.model.admin.req.AddDeptReq;
import cn.linye.grus.facade.model.admin.req.QueryDeptsReq;
import cn.linye.grus.infrastructure.BaseReq;
import cn.linye.grus.infrastructure.PagedCollection;
import cn.linye.grus.infrastructure.utils.DozerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Tim on 2017/8/4.
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private DeptRepository deptRepository;

    public PagedCollection<DeptDto> queryDeptList(QueryDeptsReq queryDeptsReq) {
        List<DeptEntity> deptEntities = deptRepository.queryDeptEntities(queryDeptsReq.getDeptName(), queryDeptsReq.getStart(), queryDeptsReq.getLength());
        int count = deptRepository.countDeptEntities(queryDeptsReq.getDeptName());
        PagedCollection<DeptDto> result = new PagedCollection<>();
        List<DeptDto> list = new ArrayList<>();
        deptEntities.stream().forEach(x -> {
            DeptDto dto = DozerUtils.getDozerMapper().map(x,DeptDto.class);
            list.add(dto);
        });
        result.setDraw(queryDeptsReq.getDraw());
        result.setRecordsFiltered(count);
        result.setData(list);
        result.setRecordsTotal(count);
        return result;
    }

    public int updateDeptEnabled(int deptId, boolean enabled) {
        return deptRepository.updateDeptEnabled(deptId,enabled);
    }

    public void addDept(AddDeptReq addDeptReq) {
        DeptEntity deptEntity = DozerUtils.mapItem(addDeptReq, DeptEntity.class);
        deptEntity.setCreatedtime(new Date());
        deptEntity.setLastmodifiedtime(new Date());
        deptEntity.setEnabled(true);
        deptMapper.insert(deptEntity);
    }

    public DeptDto getDept(int deptId) {
        DeptEntity deptEntity = deptMapper.selectByPrimaryKey(deptId);
        return DozerUtils.mapItem(deptEntity, DeptDto.class);
    }

    public void editDept(AddDeptReq addDeptReq) {
        DeptEntity oldEntity = deptMapper.selectByPrimaryKey(addDeptReq.getDeptid());
        DeptEntity deptEntity = DozerUtils.mapItem(addDeptReq, DeptEntity.class);
        deptEntity.setCreatedby(oldEntity.getCreatedby());
        deptEntity.setCreatedtime(oldEntity.getCreatedtime());
        deptEntity.setLastmodifiedtime(new Date());
        deptEntity.setEnabled(true);

        deptMapper.updateByPrimaryKey(deptEntity);
    }
}
