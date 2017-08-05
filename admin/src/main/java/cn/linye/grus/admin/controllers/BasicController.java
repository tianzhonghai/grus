package cn.linye.grus.admin.controllers;

import cn.linye.grus.domain.dtos.common.DeptDto;
import cn.linye.grus.domain.repository.DeptRepository;
import cn.linye.grus.domain.service.DeptService;
import cn.linye.grus.facade.model.admin.req.QueryDeptsReq;
import cn.linye.grus.facade.model.admin.resp.DeptResp;
import cn.linye.grus.infrastructure.PagedCollection;
import cn.linye.grus.infrastructure.utils.DozerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 基础数据
 * Created by Tim on 2017/7/31.
 */
@Controller
@RequestMapping("/basic")
public class BasicController {
    @Autowired
    private DeptService deptService;

    @RequestMapping("/deptlist")
    public String deptList(){
        return "basic/deptlist";
    }

    @RequestMapping("/getdeptlist")
    @ResponseBody
    public PagedCollection<DeptResp> getDeptList(QueryDeptsReq queryDeptsReq){
        PagedCollection<DeptResp> pagedCollection = new PagedCollection<>();
        PagedCollection<DeptDto> dtoPagedCollection = deptService.queryDeptList(queryDeptsReq);

        pagedCollection.setDraw(dtoPagedCollection.getDraw());
        pagedCollection.setRecordsFiltered(dtoPagedCollection.getRecordsFiltered());
        pagedCollection.setRecordsTotal(dtoPagedCollection.getRecordsTotal());
        pagedCollection.setData(DozerUtils.mapList(dtoPagedCollection.getData(),DeptResp.class));

        return pagedCollection;
    }
}
