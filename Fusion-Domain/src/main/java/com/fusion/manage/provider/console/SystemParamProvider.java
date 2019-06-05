package com.fusion.manage.provider.console;

import com.fusion.common.framework.dubbointerface.BaseDubboInterface;
import com.fusion.common.framework.exception.DubboProviderException;
import com.fusion.manage.entity.console.SystemParam;

import java.util.Map;

/**
 * Created by zhou.xu on 2019/3/6.
 */
public interface SystemParamProvider extends BaseDubboInterface<SystemParam>{
    /**
     * 根据key获取value,并将value转换为Map结构
     *
     * @param key
     * @return
     */
    Map<String, Object> getMapByKey(String key) throws DubboProviderException;

    /**
     * 根据key查询value
     *
     * @param key
     * @return
     * @throws DubboProviderException
     */
    String getValueByKey(String key) throws DubboProviderException;
}
