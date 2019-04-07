package com.itaem.shirotest.modules.shiro.auth;

import com.itaem.shirotest.modules.shiro.entity.Permission;
import com.itaem.shirotest.modules.shiro.entity.Role;
import com.itaem.shirotest.modules.shiro.entity.User;
import com.itaem.shirotest.modules.shiro.service.ShiroService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author CrazyJay
 * @Date 2019/4/7 14:47
 * @Version 1.0
 */
@Component
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private ShiroService shiroService;

    /**
     * 授权(验证权限时候调用)
     *@param  [principals]
     *@return org.apache.shiro.authz.AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //1. 从 PrincipalCollection 中来获取登录用户的信息
        String username = (String) principals.getPrimaryPrincipal();
        User user = shiroService.findUserByUsername(username);
        //2.添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : user.getRoles()) {
            //2.1添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            for (Permission permission : role.getPermissions()) {
                //2.1.1添加权限
                simpleAuthorizationInfo.addStringPermission(permission.getPermission());
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 认证(登陆时候调用)
     *@param  [token]
     *@return org.apache.shiro.authc.AuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 1. 获得username
        String username = (String)token.getPrincipal();
        // 2. 通过username查询用户
        User user = shiroService.findUserByUsername(username);
        // 3. 如果用户为空抛出用户不存在异常
        if (user == null) {
            throw new UnknownAccountException("用户名或密码错误");
        }

        // 4. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
        //以下信息是从数据库中获取的.
        // 4.1 principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.
        // 4.2 credentials: 密码.
        // 4.3 realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, user.getPassword(), this.getName());

        return info;
    }
}
