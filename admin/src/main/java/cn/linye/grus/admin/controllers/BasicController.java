package cn.linye.grus.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
