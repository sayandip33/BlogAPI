package com.myblog.myblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class Swagger_config {
    @Bean
    public Docket api(){
        return  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfi())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo getInfi() {
        return new ApiInfo(
                "Blog Application : By Sayandip",
                "This Project is devoloped by Sayandip sadhu",
                "1.0",
                "terms of Service",
                new Contact("Sayan","https://google.com","sayandipsadhu777@gmail.com"),
                "License of APIs",
                "License url",
                Collections.emptyList());
    }
}
