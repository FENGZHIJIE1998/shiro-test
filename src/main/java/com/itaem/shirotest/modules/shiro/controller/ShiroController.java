package com.itaem.shirotest.modules.shiro.controller;


import com.itaem.shirotest.modules.shiro.service.ShiroService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author CrazyJay
 * @Date 2019/4/7 14:45
 * @Version 1.0
 */
@RestController
public class ShiroController {
    @Autowired
    private ShiroService shiroService;

    /**
     * 登录
     */
    @ApiOperation(value = "登陆", notes = "参数:用户名 密码 ")
    @PostMapping("/login")
    public Map<String, Object> login(String username, String password) {
        Map<String, Object> map = new HashMap();
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            //用户信息
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                // 执行登录.
                currentUser.login(token);
                map.put("status", 200);
            }
            // 所有认证时异常的父类.
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
                map.put("status", 401);
                map.put("msg", "登陆失败");
                System.out.println("登录失败: " + ae.getMessage());
            }
        }
        return map;
    }

    @ApiOperation(value = "登出", notes = "参数:无 ")
    @GetMapping("/logout")
    public Map<String, Object> logout() {
        Map<String, Object> map = new HashMap();
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        map.put("status", 200);
        return map;
    }

    @ApiOperation(value = "无权限", notes = "参数:无 ")
    @GetMapping("/unauth")
    public Map<String, Object> unauth() {
        Map<String, Object> map = new HashMap();
        map.put("status", 404);
        map.put("msg","无权限");
        return map;
    }

    @ApiOperation(value = "未登录", notes = "参数:无 ")
    @GetMapping("/unlogin")
    public Map<String, Object> unlogin() {
        Map<String, Object> map = new HashMap();
        map.put("status", 404);
        map.put("msg","未登录");
        return map;
    }
}
