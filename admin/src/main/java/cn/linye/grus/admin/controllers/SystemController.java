package cn.linye.grus.admin.controllers;

import cn.linye.grus.domain.entity.generated.UserEntity;
import cn.linye.grus.domain.service.UserService;
import cn.linye.grus.facade.model.GeneralResp;
import cn.linye.grus.facade.model.PagedCollectionResp;
import cn.linye.grus.facade.model.admin.req.QueryUsersReq;
import cn.linye.grus.facade.model.admin.resp.QueryUsersResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统管理
 * Created by Tim on 2017/7/19.
 */
@Controller
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public String user(){
        return "system/userlist";
    }

    @RequestMapping("/queryusers")
    @ResponseBody
    public PagedCollectionResp<QueryUsersResp> queryUsers(QueryUsersReq QueryUsersReq){
//        QueryUsersReq queryUserReq = new QueryUsersReq();
//        queryUserReq.setAccount(account);
//        queryUserReq.setUsername(userName);
        PagedCollectionResp<QueryUsersResp> result = userService.queryUserList(QueryUsersReq);
        return result;
    }

    @RequestMapping("/lockuser")
    @ResponseBody
    public GeneralResp<String> lockUser(@RequestParam("userid") int userId, @RequestParam("locked") boolean locked){
        GeneralResp<String> resp = new GeneralResp<>();
        userService.lockUser(userId,locked);
        return resp;
    }
    @RequestMapping("/enableuser")
    @ResponseBody
    public GeneralResp<String> enableUser(@RequestParam("userid") int userId, @RequestParam("enabled") boolean enabled){
        GeneralResp<String> resp = new GeneralResp<>();
        userService.enableUser(userId,enabled);
        return resp;
    }

    @RequestMapping("/adduser")
    public String addUser(){
        return "system/useradd";
    }
    @RequestMapping("/role")
    public String role() {
        return "system/role";
    }
}
