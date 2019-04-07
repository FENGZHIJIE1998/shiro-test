package com.itaem.shirotest.modules.shiro.service.impl;

import com.itaem.shirotest.modules.shiro.dao.UserRepository;
import com.itaem.shirotest.modules.shiro.entity.User;
import com.itaem.shirotest.modules.shiro.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author CrazyJay
 * @Date 2019/4/7 14:46
 * @Version 1.0
 */
@Service
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
