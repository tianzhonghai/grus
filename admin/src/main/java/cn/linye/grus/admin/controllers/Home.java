package cn.linye.grus.admin.controllers;

import cn.linye.grus.facade.model.req.LoginReq;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by Tim on 2017/7/16.
 */
@Controller
@RequestMapping("/")
public class Home {
    /**
     * 登录
     * @return
     */
    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(LoginReq loginReq, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "login";
        }

        return "";

    }
}
