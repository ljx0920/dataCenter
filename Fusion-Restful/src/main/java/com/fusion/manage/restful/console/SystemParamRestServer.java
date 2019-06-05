package com.fusion.manage.restful.console;

import com.fusion.common.framework.Constants;
import com.fusion.common.framework.exception.DubboProviderException;
import com.fusion.common.framework.rest.JsonViewObject;
import com.fusion.common.framework.rest.impl.AbstractRestServerImpl;
import com.fusion.manage.consumer.ConsoleConsumer;
import com.fusion.manage.entity.console.SystemParam;
import com.fusion.manage.provider.console.SystemParamProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;

/**
 * Created by zhou.xu on 2019/3/6.
 */
@Api(value = "SystemParamRestServer" ,description = "系统参数-接口层")
@RequestMapping(Constants.RestPathPrefix.MODEL_CONSOLE + "systemParam")
@RestController
public class SystemParamRestServer extends AbstractRestServerImpl<SystemParam>{
    private static Logger log = LogManager.getLogger(SystemParamRestServer.class);

    @Override
    public SystemParamProvider getBaseDubboInterface() {
        return ConsoleConsumer.getSystemParamProvider();
    }

    @ApiOperation(value = "根据key查询value", notes = "根据key查询value", response = JsonViewObject.class)
    @RequestMapping(value = "getValueByKey", method = RequestMethod.GET)
    JsonViewObject getValueByKey(@ApiParam(value = "键", required = true) @QueryParam("key") String key) {
        JsonViewObject jsonViewObject = JsonViewObject.newInstance();
        String value = null;
        try {
            value = getBaseDubboInterface().getValueByKey(key);
            jsonViewObject.success((Object) value);
        } catch (DubboProviderException e) {
            jsonViewObject.fail("获取失败");
            log.error("SystemParamRestServer getValueByKey error, key=" + key, e);
        }
        return jsonViewObject;
    }
}
