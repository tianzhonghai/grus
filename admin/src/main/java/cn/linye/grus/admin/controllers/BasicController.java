package cn.linye.grus.admin.controllers;

import cn.linye.grus.facade.model.admin.resp.DeptResp;
import cn.linye.grus.infrastructure.PagedCollection;
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

    @RequestMapping("/deptlist")
    public String deptList(){
        return "basic/deptlist";
    }

    @RequestMapping("/getdeptlist")
    @ResponseBody
    public PagedCollection<DeptResp> getDeptList(){
        PagedCollection<DeptResp> pagedCollection = new PagedCollection<>();


        return pagedCollection;
    }
}
