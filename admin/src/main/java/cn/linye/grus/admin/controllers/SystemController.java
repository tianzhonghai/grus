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

        PagedCollectionResp<UserEntity> result = userService.queryUserList("admin");

        PagedCollectionResp<QueryUsersResp> respPagedCollectionResp = new PagedCollectionResp<>();
        respPagedCollectionResp.setRecordsTotal(result.getRecordsTotal());
        if(result.getData() != null){
            List<UserEntity> userEntities = result.getData();
            for (UserEntity entity : userEntities) {
                QueryUsersResp item = new QueryUsersResp();
                item.setUserid(entity.getUserid());
                item.setAccount(entity.getAccount());
                item.setCreatedtime(entity.getCreatedtime());
                list.add(item);
            }
        }
        respPagedCollectionResp.setData(list);
        return respPagedCollectionResp;
    }

    @RequestMapping("/role")
    public String role() {
        return "system/role";
    }
}
