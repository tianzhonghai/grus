package cn.linye.grus.admin.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim on 2017/7/17.
 */
public class CustomHashedCredentialsMatcher extends HashedCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //String userno = (String) token.getPrincipal();
        ShiroUser shiroUser = (ShiroUser)info.getPrincipals().getPrimaryPrincipal();
        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            // 根据登录名查询用户
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();

//            List<SysMenu> menus = new ArrayList<>();
//            SysMenu menu = new SysMenu();
//            menu.setMenuId(1);
//            menu.setMenuName("控制台");
//            menu.setUrl("/home/index");
//            menu.setIconClass("fa fa-dashboard");
//            menus.add(menu);
//
//            menu = new SysMenu();
//            menu.setMenuId(2);
//            menu.setMenuName("用户管理");
//            menu.setIconClass("fa fa-user");
//            menu.setUrl("");
//
//            SysSubMenu submenu = new SysSubMenu();
//            submenu.setMenuId(3);
//            submenu.setMenuName("用户查询");
//            submenu.setParentMenuId(2);
//            submenu.setUrl("/user/index");
//            menu.getSubMenus().add(submenu);
//
//            menus.add(menu);

//            session.setAttribute("leftMenu", menus);
        }
        return matches;
    }
}
