package com.fusion.manage.mapper.console;

import com.fusion.common.framework.mapper.BaseInterfaceMapper;
import com.fusion.manage.entity.console.SystemParam;

/**
 * Created by zhou.xu on 2019/3/6.
 */
public interface SystemParamMapper extends BaseInterfaceMapper<SystemParam>{
    String getValueByKey(String key);
}
