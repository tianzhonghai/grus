package cn.linye.grus.admin.controllers;

import cn.linye.grus.domain.entity.generated.UserEntity;
import cn.linye.grus.domain.service.UserService;
import cn.linye.grus.facade.model.GeneralResp;
import cn.linye.grus.facade.model.PagedCollectionResp;
import cn.linye.grus.facade.model.admin.req.AddUserReq;
import cn.linye.grus.facade.model.admin.req.QueryUsersReq;
import cn.linye.grus.facade.model.admin.resp.QueryUsersResp;
import cn.linye.grus.infrastructure.RespEnum;
import cn.linye.grus.infrastructure.exception.BizException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping("/user")
    public String user(){
        return "system/userlist";
    }

    @RequestMapping("/queryusers")
    @ResponseBody
    public PagedCollectionResp<QueryUsersResp> queryUsers(QueryUsersReq QueryUsersReq){
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

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    @ResponseBody
    public GeneralResp<String> addUser(AddUserReq addUserReq){
        GeneralResp<String> generalResp = new GeneralResp<>();
        try {
            userService.addUser(addUserReq);
            generalResp.setStatus(RespEnum.SUCCESS.getValue());
        }catch (Exception ex){
            BizException.throwBizException(RespEnum.FAIL,ex.getMessage(),ex);
        }
        return  generalResp;
    }

    @RequestMapping("/checkaccount")
    @ResponseBody
    public GeneralResp<String> checkAccount(@RequestParam("account")String account){
        GeneralResp<String> generalResp = new GeneralResp<>();
        if(StringUtils.isBlank(account)){
            BizException.throwIllegalArgument("账号不能为空");
        }

        UserEntity userEntity = userService.getUserEntityByAccount(account);
        if(userEntity != null){
            generalResp.setStatus(RespEnum.FAIL.getValue());
            generalResp.setMessage(account + "已存在");
            return generalResp;
        }

        generalResp.setStatus(RespEnum.SUCCESS.getValue());
        return generalResp;
    }

    @RequestMapping("/role")
    public String role() {
        return "system/rolelist";
    }

    @RequestMapping("/addrole")
    public String addRole(){
        return "system/roleadd";
    }
}
