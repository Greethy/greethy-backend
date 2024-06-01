package com.greethy.userquery.infra.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi userOpenApi() {
        var info = new Info()
                .title("User Query API Documentation")
                .version("0.0.1-SNAPSHOT")
                .description("This API handles user retrieve operations")
                .termsOfService("Term of Services")
                .contact(new Contact()
                        .name("Support Team")
                        .email("greethy.project@gmail.com")
                        .url("https://example.com/support"))
                .license(new License()
                        .name("Apache License 2.0")
                        .url("http://www.apache.org/licenses/LICENSE-2.0.html"));
        var localServer = new Server()
                .description("Local Development Environment")
                .url("http://localhost:8182");
        return GroupedOpenApi.builder()
                .group("users")
                .addOpenApiCustomizer(openApi -> openApi.info(info).servers(List.of(localServer)))
                .pathsToMatch("/api/v1/users/**")
                .build();
    }

}
