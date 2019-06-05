package com.fusion.manage.provider.console;


import com.fusion.common.framework.dubbointerface.BaseDubboInterface;
import com.fusion.common.framework.exception.DubboProviderException;
import com.fusion.manage.entity.console.User;

/**
 * Created by zhou.xu on 2019/3/5.
 */
public interface UserProvider extends BaseDubboInterface<User> {
    User findByUsername(String username) throws DubboProviderException;
}
