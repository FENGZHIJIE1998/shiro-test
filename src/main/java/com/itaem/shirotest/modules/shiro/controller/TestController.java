package com.itaem.shirotest.modules.shiro.controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author CrazyJay
 * @Date 2019/4/7 15:20
 * @Version 1.0
 */
@RestController("/test")
public class TestController {


    @RequiresPermissions({"save"}) //没有的话 AuthorizationException
    @PostMapping("/save")
    public Map<String, Object> selectRole() {
        System.out.println("save");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        map.put("msg", "当前用户有save的权力");
        return map;
    }

    @RequiresPermissions({"delete"}) //没有的话 AuthorizationException
    @PostMapping("/delete")
    public Map<String, Object> managerRole() {
        System.out.println("delete");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        map.put("msg", "当前用户有delete的权力");
        return map;
    }

    @RequiresPermissions({"update"}) //没有的话 AuthorizationException
    @PostMapping("update")
    public Map<String, Object> selectUser() {
        System.out.println("update");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        map.put("msg", "当前用户有update的权力");
        return map;
    }

    @RequiresPermissions({"select"}) //没有的话 AuthorizationException
    @PostMapping("select")
    public Map<String, Object> managerUser() {
        System.out.println("select");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        map.put("msg", "当前用户有select的权力");
        return map;
    }

    @RequiresRoles({"vip"}) //没有的话 AuthorizationException
    @PostMapping("/vip")
    public Map<String, Object> vip() {
        System.out.println("vip");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        map.put("msg", "当前用户有VIP角色");
        return map;
    }
    @RequiresRoles({"svip"}) //没有的话 AuthorizationException
    @PostMapping("/svip")
    public Map<String, Object> SVIP() {
        System.out.println("svip");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        map.put("msg", "当前用户有SVIP角色");
        return map;
    }
    @RequiresRoles({"p"}) //没有的话 AuthorizationException
    @PostMapping("/p")
    public Map<String, Object> P() {
        System.out.println("p");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        map.put("msg", "当前用户有P角色");
        return map;
    }
}
