package com.fusion.manage;

import com.fusion.common.framework.spring.ServiceBeanContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author weijun.yu
 */
@SpringBootApplication
@EnableSwagger2
@ImportResource(locations = {"classpath:configs/consumer/*.xml"})
public class FusionRestApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(FusionRestApplication.class);
        ServiceBeanContext.loadContext(context);
    }
}

