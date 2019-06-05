package com.fusion.manage.provider.console;

import com.fusion.common.framework.dubbointerface.impl.BaseDubboInterfaceImpl;
import com.fusion.common.framework.exception.DubboProviderException;
import com.fusion.common.framework.mapper.BaseInterfaceMapper;
import com.fusion.manage.entity.console.SystemParam;
import com.fusion.manage.mapper.console.SystemParamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhou.xu on 2019/3/6.
 */
public class SystemParamProviderImpl extends BaseDubboInterfaceImpl<SystemParam> implements SystemParamProvider{
    @Autowired
    @Qualifier("systemParamMapper")
    private SystemParamMapper systemParamMapper;

    @Override
    public BaseInterfaceMapper<SystemParam> getBaseInterfaceMapper() {
        return systemParamMapper;
    }

    @Override
    public Map<String, Object> getMapByKey(String key) throws DubboProviderException {
        String mapStr = systemParamMapper.getValueByKey(key);
        if (mapStr == null || "".equals(mapStr)) {
            throw new DubboProviderException("该键没有相对应的值信息");
        }
        String[] mapStrList = mapStr.split(",");
        Map<String, Object> map = new HashMap<>();
        for (String str : mapStrList) {
            map.put(str.split(":")[0], str.split(":")[1]);
        }
        return map;
    }

    @Override
    public String getValueByKey(String key) throws DubboProviderException {
        return systemParamMapper.getValueByKey(key);
    }
}
