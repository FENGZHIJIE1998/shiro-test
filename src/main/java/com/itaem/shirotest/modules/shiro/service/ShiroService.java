package com.itaem.shirotest.modules.shiro.service;

import com.itaem.shirotest.modules.shiro.entity.User;

/**
 * @Author CrazyJay
 * @Date 2019/4/7 14:46
 * @Version 1.0
 */
public interface ShiroService {
    User findUserByUsername(String username);

}
