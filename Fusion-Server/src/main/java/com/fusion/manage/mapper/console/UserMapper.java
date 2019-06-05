package com.fusion.manage.mapper.console;

import com.fusion.common.framework.mapper.BaseInterfaceMapper;
import com.fusion.manage.entity.console.User;

/**
 * Created by zhou.xu on 2019/3/5.
 */
public interface UserMapper extends BaseInterfaceMapper<User>{
    User findByUsername(String username);
}
