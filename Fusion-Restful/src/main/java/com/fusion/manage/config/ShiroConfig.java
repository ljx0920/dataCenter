package com.fusion.manage.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by yaju.jiao on 2019/3/5.
 */
@Configuration
public class ShiroConfig {
    private static Logger log = LogManager.getLogger(ShiroConfig.class);
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        log.info("[ShiroConfig] shiroFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //配置不会被拦截的链接 顺序判断
//        filterChainDefinitionMap.put("/static/**", "anon");
        //配置退出过滤器，其中具体的退出代码Shrio已经实现
//        filterChainDefinitionMap.put("/fusion/console/user/logout", "logout");
        //authc:所有的url都必须通过认证才可以访问；anon:所有url都可以匿名访问
        filterChainDefinitionMap.put("/*", "anon");
        shiroFilterFactoryBean.setLoginUrl("/swagger-ui.html");

        //未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(PasswordHelper.ALGORITHM_NAME);
        hashedCredentialsMatcher.setHashIterations(PasswordHelper.HASH_ITERATIONS);
        return hashedCredentialsMatcher;
    }
    @Bean
    public UserRealm myShiroRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    @Bean
    public PasswordHelper passwordHelper() {
        return new PasswordHelper();
    }
}
