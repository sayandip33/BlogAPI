package com.myblog.myblog.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.ExternalDocumentation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI blogAppOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Blog Application : By Sayandip")
                .description("This project is developed by Sayandip Sadhu")
                .version("1.0")
                .contact(new Contact()
                    .name("Sayandip Sadhu")
                    .url("https://google.com")
                    .email("sayandipsadhu777@gmail.com"))
                .license(new License()
                    .name("License of APIs")
                    .url("https://license.example.com")))
            .externalDocs(new ExternalDocumentation()
                .description("Project Docs")
                .url("https://docs.example.com"));
    }
}
