package com.greethy.personal.application.rest.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class UserEndpointRouter {

    @Bean
    public RouterFunction<ServerResponse> route(UserEndpointHandler userHandler) {
        return RouterFunctions.route()
                .POST("/api/v1/user", accept(MediaType.APPLICATION_JSON), userHandler::save)
                .GET("/api/v1/users", request -> userHandler.findAll())
                .build();
    }
}
