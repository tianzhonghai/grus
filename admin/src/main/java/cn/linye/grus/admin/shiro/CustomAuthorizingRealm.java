package cn.linye.grus.admin.shiro;

import cn.linye.grus.domain.dtos.PermissionRespDto;
import cn.linye.grus.domain.dtos.UserDto;
import cn.linye.grus.domain.entity.generated.PermissionEntity;
import cn.linye.grus.domain.entity.generated.UserEntity;
import cn.linye.grus.domain.service.PermissionService;
import cn.linye.grus.domain.service.UserService;
import cn.linye.grus.infrastructure.utils.SecretUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Tim on 2017/7/17.
 */
@Component
public class CustomAuthorizingRealm extends AuthorizingRealm {
    public CustomAuthorizingRealm(){
        super(new AllowAllCredentialsMatcher());
        setAuthenticationTokenClass(UsernamePasswordToken.class);

        //FIXME: 暂时禁用Cache
        //super.setCachingEnabled(false);
        super.setCachingEnabled(true);
    }

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ShiroUser user = (ShiroUser) principals.getPrimaryPrincipal();
        //String username = (String) principals.fromRealm(getName()).iterator().next();
        //SysUser user = userService.getUserVoByUserName(username);
        if(user != null){
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            Set<String> shiroPermissions = new HashSet<>();
            Set<String> roleSet = new HashSet<>();

            List<PermissionRespDto> permissions = permissionService.getUserPermissions(user.getUserId());
            for (PermissionRespDto dto : permissions) {
//                if(! roleSet.contains(roleAndPermissionPO.getRoleId().toString())) {
//                    roleSet.add(roleAndPermissionPO.getRoleId().toString());
//                }
                shiroPermissions.add(dto.getPermissionname());
            }
            //authorizationInfo.setRoles(roleSet);
            authorizationInfo.setStringPermissions(shiroPermissions); //权限集合，基于角色的可以不设置
            return authorizationInfo;
        }

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken )token;
        String username = (String) token.getPrincipal();
        UserDto userDto = userService.getUserEntityByAccount(username);
        String password = new String((char[]) token.getCredentials());

        // 账号不存在
        if (userDto == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }
        // 密码错误
        if (!SecretUtils.MD5(password).equals(userDto.getPassword())) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }
        // 账号锁定
        if (userDto.getLocked()) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        ShiroUser shiroUser = new ShiroUser();
        shiroUser.setUserId(userDto.getUserid());
        shiroUser.setAccount(userDto.getAccount());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(shiroUser, SecretUtils.MD5(password), getName());
        return info;
    }
}
