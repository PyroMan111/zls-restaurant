package com.wnxy.waiter.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableKnife4j
@EnableSwagger2WebMvc
public class SwaggerConfig {
    /**
     * 创建在线文档摘要对象
     *
     * @return
     */
    @Bean
    public Docket createDocket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        // 设置文档摘要信息：标题、版本、描述
        docket.apiInfo(new ApiInfoBuilder()
                .title("员工端-服务员模块微服务")
                .version("1.0")
                .description("Api描述")
                .build()
        );
        docket.select()
                // 扫描的API路径
                .apis(RequestHandlerSelectors
                        .basePackage("com.wnxy.waiter.controller"))
                // 设置对指定路径下的任意类生成文档
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}