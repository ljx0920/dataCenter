package com.fusion.manage.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author yifan.zhang
 */
@Configuration
public class SwaggerConfig {

    /**
     * Swagger的配置操作
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("服务中台开发框架的API文档")
//                .description("简单优雅的restfun风格，http://blog.csdn.net/saytime")
//                .termsOfServiceUrl("http://127.0.0.1:15082/swagger-ui.html#/")
                .version("1.0")
                .build();
    }
}
