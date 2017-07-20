package cn.linye.grus.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统管理
 * Created by Tim on 2017/7/19.
 */
@Controller
@RequestMapping("/system")
public class SystemController {

    @RequestMapping("/user")
    public String user(){
        return "system/user";
    }

    @RequestMapping("/queryusers")
    @ResponseBody
    public String queryUsers(){
        return "";
    }

    @RequestMapping("/role")
    public String role() {
        return "system/role";
    }
}
