package com.fusion.manage.consumer;

import com.fusion.common.framework.spring.ServiceBeanContext;
import com.fusion.manage.provider.console.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by yaju.jiao on 2019/3/4.
 */
public class ConsoleConsumer {

    private static final Logger log = LogManager.getLogger(ConsoleConsumer.class);

    public static MenuProvider getMenuProvider() {
        try {
            return ServiceBeanContext.getBean(MenuProvider.class);
        } catch (Exception e) {
            log.error("获取 getMenuProvider ServiceBean 失败！", e);
            return null;
        }
    }

    public static UserProvider getUserProvider() {
        try {
            return ServiceBeanContext.getBean(UserProvider.class);
        } catch (Exception e) {
            log.error("获取 getUserProvider ServiceBean 失败！", e);
            return null;
        }
    }

    public static SystemParamProvider getSystemParamProvider() {
        try {
            return ServiceBeanContext.getBean(SystemParamProvider.class);
        } catch (Exception e) {
            log.error("获取 SystemParamProvider ServiceBean 失败！", e);
            return null;
        }
    }

    public static SystemParamCatalogProvider getSystemParamCatalogProvider() {
        try {
            return ServiceBeanContext.getBean(SystemParamCatalogProvider.class);
        } catch (Exception e) {
            log.error("获取 SystemParamCatalogProvider ServiceBean 失败！", e);
            return null;
        }
    }

}
