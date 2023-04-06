package com.fqyc.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author lck
 * @Date 2023/3/20 19:53
 * @Version 1.0
 * @Desc
 */
@Configuration
@EnableSwagger2
public class SwagerrConfiguration {
    private static ApiInfo DEFAULT = null;
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2);
    }
}
