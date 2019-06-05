package com.fusion.manage.restful.console;


import com.fusion.common.framework.Constants;
import com.fusion.common.framework.rest.impl.AbstractRestServerImpl;
import com.fusion.manage.consumer.ConsoleConsumer;
import com.fusion.manage.entity.console.SystemParamCatalog;
import com.fusion.manage.provider.console.SystemParamCatalogProvider;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhou.xu on 2019/3/6.
 */
@Api(value = "SystemParamCatalogRestServer" , description = "系统参数类别-接口层")
@RequestMapping(Constants.RestPathPrefix.MODEL_CONSOLE + "systemParamCatalog")
@RestController
public class SystemParamCatalogRestServer extends AbstractRestServerImpl<SystemParamCatalog>{
    private static Logger log = LogManager.getLogger(SystemParamCatalogRestServer.class);

    @Override
    public SystemParamCatalogProvider getBaseDubboInterface() {
        return ConsoleConsumer.getSystemParamCatalogProvider();
    }
}
