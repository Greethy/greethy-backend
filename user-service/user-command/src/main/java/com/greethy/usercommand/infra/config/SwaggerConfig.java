package com.greethy.usercommand.infra.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI userOpenApi(@Value("${springdoc.swagger-ui.security-scheme-name}") String securitySchemeName) {
        var components = new Components()
                .addSecuritySchemes(securitySchemeName,
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"));
        var info = new Info()
                .title("User Command API Documentation")
                .version("0.0.1-SNAPSHOT")
                .description("This API List handles user change resources operations")
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
                .url("http://localhost:8181");

        return new OpenAPI()
                .info(info)
                .servers(List.of(localServer))
                .components(components)
                .security(List.of(new SecurityRequirement().addList(securitySchemeName)));

    }

}
