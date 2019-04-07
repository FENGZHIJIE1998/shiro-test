package com.itaem.shirotest.modules.shiro.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author CrazyJay
 * @Date 2019/4/7 14:45
 * @Version 1.0
 */
@Getter
@Setter
@Entity
public class Permission {

    @Id
    private Integer permissionId;
    private String permissionName;
    private String permission;
}
