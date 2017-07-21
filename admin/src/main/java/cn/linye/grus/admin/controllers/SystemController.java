package cn.linye.grus.admin.controllers;

import cn.linye.grus.domain.entity.generated.UserEntity;
import cn.linye.grus.domain.service.UserService;
import cn.linye.grus.facade.model.PagedCollectionResp;
import cn.linye.grus.facade.model.admin.resp.QueryUsersResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/user")
    public String user(){
        return "system/user";
    }

    @RequestMapping("/queryusers")
    @ResponseBody
    public PagedCollectionResp<QueryUsersResp> queryUsers(){
        List<QueryUsersResp> list = new ArrayList<>();

        PagedCollectionResp<UserEntity> result = userService.queryUserList("");

        PagedCollectionResp<QueryUsersResp> respPagedCollectionResp = new PagedCollectionResp<>();
        respPagedCollectionResp.setRecordsTotal(0);
        respPagedCollectionResp.setData(list);
        return respPagedCollectionResp;
    }

    @RequestMapping("/role")
    public String role() {
        return "system/role";
    }
}
