package com.itaem.shirotest.config;


import com.itaem.shirotest.modules.shiro.auth.AuthRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author CrazyJay
 * @Date 2019/3/30 21:50
 * @Version 1.0
 */
@Configuration
public class ShiroConfig {



    @Bean("securityManager")
    public SecurityManager securityManager(AuthRealm authRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //注册自己的Realm
        securityManager.setRealm(authRealm);
        securityManager.setRememberMeManager(null);
        return securityManager;
    }

    /**
     *   配置ShiroFilter
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        //设置SecurityManager
        shiroFilter.setSecurityManager(securityManager);
    
        //设置未登录时候的路径
        shiroFilter.setLoginUrl("/unlogin");
        //设置拦截器
        Map<String, String> filterMap = new LinkedHashMap<>();
        //注意会存在前面优先级高于后面情况
        filterMap.put("/login", "anon");
        //测试页面
        filterMap.put("/swagger/**", "anon");
        filterMap.put("/v2/**", "anon");
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("logout","logout");
        //所以这句话要放在最后，这句话的意思是除了上面配置的路径，剩下的全部路径都需要认证通过才能访问
        filterMap.put("/**", "authc");


        shiroFilter.setFilterChainDefinitionMap(filterMap);

        return shiroFilter;
    }

    /**
     * 配置SecurityManager的生命周期处理器
     */
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启shiro注解功能
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }


}
