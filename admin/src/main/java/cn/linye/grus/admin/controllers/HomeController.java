package cn.linye.grus.admin.controllers;

import cn.linye.grus.admin.shiro.ShiroUser;
import cn.linye.grus.domain.entity.generated.PermissionEntity;
import cn.linye.grus.domain.service.PermissionService;
import cn.linye.grus.domain.service.UserService;
import cn.linye.grus.facade.model.GeneralResp;
import cn.linye.grus.facade.model.admin.req.LoginReq;
import cn.linye.grus.facade.model.admin.resp.GetUserPermissionsResp;
import cn.linye.grus.infrastructure.caching.GuavaCache;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim on 2017/7/16.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private PermissionService permissionService;

    /**
     * 登录
     * @return
     */
    @RequestMapping("login")
    public String login(){
        return "home/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(LoginReq loginReq, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "home/login";
        }

        String account = loginReq.getAccount();
        UsernamePasswordToken token = new UsernamePasswordToken(account, loginReq.getPassword());

        Subject currentUser = SecurityUtils.getSubject();
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            currentUser.login(token);
        }catch(UnknownAccountException uae){
            redirectAttributes.addFlashAttribute("message", "未知账户");
        }catch(IncorrectCredentialsException ice){
            redirectAttributes.addFlashAttribute("message", "密码不正确");
        }catch(LockedAccountException lae){
            redirectAttributes.addFlashAttribute("message", "账户已锁定");
        }catch(ExcessiveAttemptsException eae){
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
        }catch(AuthenticationException ae){
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            logger.info("对用户[" + account + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
        }
        //验证是否登录成功
        if(currentUser.isAuthenticated()){
            SecurityUtils.getSubject().getSession().setTimeout(3600000L);
            return "redirect:/index";
        }else{
            token.clear();
            return "redirect:/login";
        }
    }

    @RequestMapping("index")
    public String index(){
        return "home/index";
    }

    @RequestMapping("top")
    public String top(){
        return "home/top";
    }

    @RequestMapping("nav")
    public String nav(){
        return "home/nav";
    }

    @RequestMapping("home/getUserPermissions")
    @ResponseBody
    public GeneralResp<List<GetUserPermissionsResp>> getUserPermissions(){
        GeneralResp<List<GetUserPermissionsResp>> result = new GeneralResp<>();
        List<GetUserPermissionsResp> list = new ArrayList<>();

        ShiroUser shiroUser = (ShiroUser)SecurityUtils.getSubject().getPrincipal();

        List<PermissionEntity> entities = permissionService.getUserPermissions(shiroUser.getUserId());
        if(entities != null) {

            for (PermissionEntity entity : entities) {
                GetUserPermissionsResp resp = new GetUserPermissionsResp();
                resp.setPermissionid(entity.getPermissionid());
                resp.setIsmenu(entity.getIsmenu());
                resp.setPermissioncode(entity.getPermissioncode());
                resp.setPermissionname(entity.getPermissionname());
                resp.setParentPermissionId(entity.getParentpermissionid());
                resp.setUrl(entity.getUrl());
                list.add(resp);
            }
        }
        result.setData(list);
        return result;
    }

    @RequestMapping("main")
    public String main(){
        return "home/main";
    }
}
