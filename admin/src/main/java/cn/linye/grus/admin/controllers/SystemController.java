package cn.linye.grus.admin.controllers;

import cn.linye.grus.domain.dtos.PermissionWithCheckedRespDto;
import cn.linye.grus.domain.dtos.RoleDto;
import cn.linye.grus.domain.entity.UserWithProfileEntity;
import cn.linye.grus.domain.entity.generated.UserEntity;
import cn.linye.grus.domain.service.PermissionService;
import cn.linye.grus.domain.service.RoleService;
import cn.linye.grus.domain.service.UserService;
import cn.linye.grus.facade.model.admin.req.QueryRolesReq;
import cn.linye.grus.facade.model.admin.resp.QueryRolesResp;
import cn.linye.grus.infrastructure.GeneralResp;
import cn.linye.grus.infrastructure.PagedCollection;
import cn.linye.grus.facade.model.admin.req.AddRoleReq;
import cn.linye.grus.facade.model.admin.req.AddUserReq;
import cn.linye.grus.facade.model.admin.req.QueryUsersReq;
import cn.linye.grus.facade.model.admin.resp.PermissionTreeNodeResp;
import cn.linye.grus.facade.model.admin.resp.QueryUsersResp;
import cn.linye.grus.infrastructure.RespEnum;
import cn.linye.grus.infrastructure.exception.BizException;
import cn.linye.grus.infrastructure.utils.DozerUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping("/userlist")
    public String user(){
        return "system/userlist";
    }

    @RequestMapping("/queryuserlist")
    @ResponseBody
    public PagedCollection<QueryUsersResp> queryUsers(QueryUsersReq QueryUsersReq){
        PagedCollection<QueryUsersResp> result = userService.queryUserList(QueryUsersReq);
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

    @RequestMapping("/edituser")
    public String editUser(@RequestParam("userid")int userId, Model model){
        UserWithProfileEntity userWithProfileEntity = userService.getUserWithProfileEntity(userId);
        AddUserReq addUserReq = DozerUtils.getDozerMapper().map(userWithProfileEntity, AddUserReq.class);
        model.addAttribute("user", addUserReq);
        return "system/useredit";
    }

    @RequestMapping(value = "/edituser", method = RequestMethod.POST)
    public GeneralResp<String> editUser(AddUserReq addUserReq){
        addUserReq.doValidate();
        GeneralResp<String> generalResp = new GeneralResp<>();
        userService.editUser(addUserReq);
        return generalResp.success();
    }

    @RequestMapping("/checkaccountforedit")
    @ResponseBody
    public GeneralResp<String> checkAccountForEdit(@RequestParam("account")String account, @RequestParam("old")String old){
        GeneralResp<String> generalResp = new GeneralResp<>();
        if(StringUtils.isBlank(account)){
            BizException.throwIllegalArgument("账号不能为空");
        }

        if(account.equalsIgnoreCase(old)){
            generalResp.setStatus(RespEnum.FAIL.getValue());
            return generalResp;
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

    @RequestMapping("/rolelist")
    public String role() {
        return "system/rolelist";
    }

    @RequestMapping("/queryrolelist")
    @ResponseBody
    public PagedCollection<QueryRolesResp> queryRoleList(QueryRolesReq queryRolesReq){
        PagedCollection<RoleDto> result = roleService.queryRoleList(queryRolesReq);

        List<QueryRolesResp> list = DozerUtils.mapList(result.getData(),QueryRolesResp.class);
        PagedCollection<QueryRolesResp> pagedCollection = new PagedCollection<>();
        pagedCollection.setData(list);
        pagedCollection.setRecordsTotal(result.getRecordsTotal());
        pagedCollection.setDraw(result.getDraw());
        pagedCollection.setRecordsFiltered(result.getRecordsFiltered());
        return pagedCollection;
    }

    @RequestMapping("/addrole")
    public String addRole(){
        return "system/roleadd";
    }

    @RequestMapping(value = "/addrole" , method = RequestMethod.POST)
    @ResponseBody
    public GeneralResp<String> addRole(@RequestBody AddRoleReq addRoleReq){
        addRoleReq.doValidate();
        GeneralResp<String> generalResp = new GeneralResp<>();
        roleService.addRole(addRoleReq);
        return generalResp.success();
    }

    @RequestMapping("/editrole")
    public String editRole(@RequestParam("roleid")Integer roleId, Model model) {
        RoleDto roleDto = roleService.getRoleById(roleId);
        AddRoleReq addRoleReq = DozerUtils.getDozerMapper().map(roleDto, AddRoleReq.class);
        model.addAttribute("role", addRoleReq);
        return "/system/roleedit";
    }

    @RequestMapping("/editrole")
    @ResponseBody
    public GeneralResp<String> editrole(@RequestBody AddRoleReq addRoleReq){
        GeneralResp<String> generalResp = new GeneralResp<>();

        return generalResp;
    }
    @RequestMapping("/getAllPermissionsWithChecked")
    @ResponseBody
    public List<PermissionTreeNodeResp> getAllPermissionsWithChecked(@RequestParam(value = "roleid", defaultValue = "0")Integer roleId) {
        GeneralResp<List<PermissionTreeNodeResp>> resp = new GeneralResp<>();
        List<PermissionWithCheckedRespDto> dtos = permissionService.getAllPermissionsWithChecked(roleId.intValue());
        List<PermissionTreeNodeResp> list = new ArrayList<>();
        for (PermissionWithCheckedRespDto dto: dtos ) {
            PermissionTreeNodeResp node = new PermissionTreeNodeResp();
            node.setId(dto.getPermissionid());
            node.setName(dto.getPermissionname());
            node.setPid(dto.getParentpermissionid());
            if(dto.getChecked() == null || dto.getChecked() == 0) {
                node.setChecked(false);
            }else {
                node.setChecked(true);
            }

            node.setOpen(false);
            if(dto.getParentpermissionid() == null || dto.getParentpermissionid() == 0){
                node.setOpen(true);
            }
            list.add(node);
        }

        return list;
    }
}
