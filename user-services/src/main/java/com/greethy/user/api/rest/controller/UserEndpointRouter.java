package com.greethy.user.api.rest.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class UserEndpointRouter {

    @Bean
    public RouterFunction<ServerResponse> route(UserCommandsEndpointHandler userCommandEndpointHandler,
                                                UserQueriesEndpointHandler userQueriesEndpointHandler) {
        return RouterFunctions.route()
                .POST("/api/v1/user",
                        accept(MediaType.APPLICATION_JSON),
                        userCommandEndpointHandler::registerUser)
                .GET("api/v1/user/",
                        contentType(MediaType.ALL),
                        request -> userQueriesEndpointHandler.findAllUser())
                .GET("api/v1/user",
                        queryParam("page", t -> true).and(queryParam("size", t -> true)),
                        userQueriesEndpointHandler::findAllUserWithPageable)
                .GET("/api/v1/user",
                        accept(MediaType.APPLICATION_JSON),
                        userQueriesEndpointHandler::findUserByUsername)
                .PUT("/api/v1/user/profile",
                        accept(MediaType.APPLICATION_JSON),
                        userCommandEndpointHandler::updateUserProfile)
                .build();
    }

}
