package com.itaem.shirotest.modules.shiro.dao;

import com.itaem.shirotest.modules.shiro.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author CrazyJay
 * @Date 2019/3/30 22:05
 * @Version 1.0
 */
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
}
