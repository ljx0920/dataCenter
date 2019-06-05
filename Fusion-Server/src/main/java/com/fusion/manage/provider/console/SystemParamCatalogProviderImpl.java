package com.fusion.manage.provider.console;

import com.fusion.common.framework.dubbointerface.impl.BaseDubboInterfaceImpl;
import com.fusion.common.framework.exception.DubboProviderException;
import com.fusion.common.framework.mapper.BaseInterfaceMapper;
import com.fusion.common.framework.rest.JsonViewObject;
import com.fusion.manage.entity.console.SystemParam;
import com.fusion.manage.entity.console.SystemParamCatalog;
import com.fusion.manage.mapper.console.SystemParamCatalogMapper;
import com.fusion.manage.mapper.console.SystemParamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import springfox.documentation.spring.web.json.Json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhou.xu on 2019/3/6.
 */
public class SystemParamCatalogProviderImpl extends BaseDubboInterfaceImpl<SystemParamCatalog> implements SystemParamCatalogProvider{

   @Autowired
   @Qualifier("systemParamCatalogMapper")
   private SystemParamCatalogMapper systemParamCatalogMapper;

    @Autowired
    @Qualifier("systemParamMapper")
    private SystemParamMapper systemParamMapper;

    @Override
    public BaseInterfaceMapper<SystemParamCatalog> getBaseInterfaceMapper() {
        return systemParamCatalogMapper;
    }

    @Override
    public JsonViewObject deleteById(String id) throws DubboProviderException {
        JsonViewObject jsonViewObject = JsonViewObject.newInstance();
        Map<String, Object> map = new HashMap<>();
        map.put("systemParamCatalogId", id);
        map.put("parentId",id);
        List<SystemParam> systemParamList = systemParamMapper.findByMap(map);
        List<SystemParamCatalog> systemParamCatalogList = systemParamCatalogMapper.findByMap(map);
        if (!systemParamList.isEmpty()) {
            jsonViewObject.fail("被删除系统参数类别下仍有参数");
            throw new DubboProviderException("被删除系统参数类别下仍有参数");
        }else if (!systemParamCatalogList.isEmpty()){
            jsonViewObject.fail("被删除系统参数类别下仍有子类别");
            throw new DubboProviderException("被删除系统参数类别下仍有子类别");
        }
        super.deleteById(id);
        return jsonViewObject;
    }
}
