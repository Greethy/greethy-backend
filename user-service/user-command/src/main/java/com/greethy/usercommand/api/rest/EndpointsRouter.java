package com.greethy.usercommand.api.rest;

import com.greethy.usercommand.api.rest.handler.UserCommandHandler;
import com.greethy.usercommon.annotation.CommandSwagger;
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
    public RouterFunction<ServerResponse> route(UserCommandHandler userCommandHandler) {
        return RouterFunctions.route()
                .path( "api/v1/users", builder -> builder
                        .nest(accept(MediaType.APPLICATION_JSON), routerBuilder -> routerBuilder
                                .POST(userCommandHandler::registerUser)
                                .PUT("{user-id}", userCommandHandler::updateUser)
                                .DELETE("{user-id}/temporary", userCommandHandler::deleteUserTemporary)
                        ).build()
                ).build();
    }

}
