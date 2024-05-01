package com.greethy.nutrition.infra.config;

import java.util.List;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi nutritionOpenApi() {
        var info = new Info()
                .title("Nutrition API Documentation")
                .version("1.0.0")
                .description("This is description")
                .termsOfService("Term of Services");
        var localServer =
                new Server().description("Local Development Environment").url("http://localhost:8092");
        return GroupedOpenApi.builder()
                .group("nutrition")
                .addOpenApiCustomizer(openApi -> openApi.info(info).servers(List.of(localServer)))
                .pathsToMatch("/api/v1/**")
                .build();
    }
}
