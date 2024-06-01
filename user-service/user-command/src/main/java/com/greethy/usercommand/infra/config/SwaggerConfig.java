package com.greethy.usercommand.infra.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class SwaggerConfig {

    @Bean
    public GroupedOpenApi userOpenApi() {
        var info = new Info()
                .title("User API Documentation")
                .version("0.0.1-SNAPSHOT")
                .description("This is description")
                .termsOfService("Term of Services");
        var localServer = new Server()
                .description("Local Development Environment")
                .url("http://localhost:8181");
        return GroupedOpenApi.builder()
                .group("users")
                .addOpenApiCustomizer(openApi -> openApi.info(info).servers(List.of(localServer)))
                .pathsToMatch("/api/v1/users/**")
                .build();
    }

}
