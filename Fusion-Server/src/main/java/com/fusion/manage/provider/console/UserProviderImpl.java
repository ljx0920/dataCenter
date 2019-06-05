package com.fusion.manage.provider.console;

import com.fusion.common.framework.dubbointerface.impl.BaseDubboInterfaceImpl;
import com.fusion.common.framework.exception.DubboProviderException;
import com.fusion.common.framework.mapper.BaseInterfaceMapper;
import com.fusion.common.framework.rest.JsonViewObject;
import com.fusion.manage.entity.console.User;
import com.fusion.manage.mapper.console.MenuMapper;
import com.fusion.manage.mapper.console.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by zhou.xu on 2019/3/5.
 */
public class UserProviderImpl extends BaseDubboInterfaceImpl<User> implements UserProvider{

    @Autowired
    @Qualifier("userMapper")
    private UserMapper userMapper;

    @Autowired
    @Qualifier("menuMapper")
    private MenuMapper menuMapper;

    @Override
    public BaseInterfaceMapper<User> getBaseInterfaceMapper() {
        return userMapper;
    }

    @Override
    public User findByUsername(String username) throws DubboProviderException {
        User user = userMapper.findByUsername(username);
        if (user != null) {
            user.setMenuList(menuMapper.findAll());
        }
        return user;
    }

    @Override
    public JsonViewObject save(User entity) throws DubboProviderException {
        JsonViewObject jsonViewObject = JsonViewObject.newInstance();
        entity.setDelFlag(0);
        if (entity.getName() == null) {
            return jsonViewObject.fail("用户姓名为空");
        }
        if (entity.getMobilePhone() == null) {
            return jsonViewObject.fail("手机号为空");
        }
        if (entity.getStartDate() == null) {
            return jsonViewObject.fail("开始时间为空");
        }
        if (entity.getEndDate() == null) {
            return jsonViewObject.fail("结束时间为空");
        }
        return super.save(entity);
    }
}
