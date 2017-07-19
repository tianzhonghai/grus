package cn.linye.grus.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Tim on 2017/7/19.
 */
@Controller
@RequestMapping("/system")
public class SystemController {

    @RequestMapping("/user")
    public String user(){
        return "system/user";
    }
}
