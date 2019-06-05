package com.fusion.manage.config;

import com.fusion.common.framework.exception.DubboProviderException;
import com.fusion.manage.entity.console.User;
import com.fusion.manage.provider.console.UserProvider;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yaju.jiao on 2019/3/5.
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserProvider userProvider;
    //shiro的权限信息配置
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = (String) principals.getPrimaryPrincipal();
        User user = null;
        try {
            user = userProvider.findByUsername(username);
        } catch (DubboProviderException e) {
            e.printStackTrace();
        }
        Set<String> menuUrlList = new HashSet<>();
        user.getMenuList().forEach(menu -> { menuUrlList.add(menu.getUrl()); });
        authorizationInfo.setStringPermissions(menuUrlList);
        return authorizationInfo;
    }

    //根据token获取认证信息authenticationInfo
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        User user;
        try {
            user = userProvider.findByUsername(username);
        } catch (DubboProviderException e) {
            throw new RuntimeException("获取用户信息失败");
        }
        if (user == null) {
            throw new UnknownAccountException();
        }
        if (user.getDelFlag() == 1) {
            throw new DisabledAccountException();
        }
        Date now = new Date();
        if (now.getTime() < user.getStartDate().getTime() || now.getTime() > user.getEndDate().getTime()) {
            throw new ExpiredCredentialsException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(),
                user.getPwd(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                getName()
        );
        return authenticationInfo;
    }
}
