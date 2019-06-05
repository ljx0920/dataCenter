package com.fusion.manage;

import com.fusion.common.framework.spring.ServiceBeanContext;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author weijun.yu
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.fusion.manage.provider"})
@EntityScan(basePackages = {"com.fusion.manage.entity"})
@ImportResource(locations = {"classpath:configs/provider/*.xml"})
@MapperScan(basePackages = {"com.fusion.manage.mapper"})
//注解事务管理
@EnableTransactionManagement
public class FusionServerApplication {

    public static void main(String[] args) {
//         start embedded zookeeper server
        new EmbeddedZooKeeper(12181, false).start();
        ApplicationContext context = SpringApplication.run(FusionServerApplication.class);
        ServiceBeanContext.loadContext(context);

    }
}
