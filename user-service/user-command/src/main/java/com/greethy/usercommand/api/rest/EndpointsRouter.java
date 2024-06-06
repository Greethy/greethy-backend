package com.greethy.usercommand.api.rest;

import com.greethy.usercommand.api.rest.handler.AuthCommandHandler;
import com.greethy.usercommand.api.rest.handler.UserCommandHandler;
import com.greethy.usercommand.api.rest.swagger.CommandSwagger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class EndpointsRouter {

    @Bean
    @CommandSwagger
    public RouterFunction<ServerResponse> route(UserCommandHandler userHandler,
                                                AuthCommandHandler authHandler) {
        return RouterFunctions.route()
                .path( "/api/v1/users", builder -> builder
                        .nest(accept(MediaType.APPLICATION_JSON), routerBuilder -> routerBuilder
                                .POST(userHandler::registerUser)
                                .PUT("{user-id}", userHandler::updateUser)
                                .DELETE("{user-id}/temporary", userHandler::deleteUserTemporary)
                        ).build()
                ).path("/auth", builder -> builder
                        .nest(accept(MediaType.APPLICATION_JSON), routerBuilder -> routerBuilder
                                .POST("login", authHandler::authenticate)
                                .POST("register", authHandler::registerGreethyUser)
                        ).build()
                ).build();
    }

}
