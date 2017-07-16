package cn.linye.grus.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Tim on 2017/7/16.
 */
@Controller
@RequestMapping("/")
public class Home {
    public String login(){
        return "login";
    }
}
