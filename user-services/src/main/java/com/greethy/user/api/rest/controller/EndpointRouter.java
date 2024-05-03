package com.greethy.user.api.rest.controller;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

import java.util.Objects;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.greethy.user.api.rest.controller.handler.UserCommandsEndpointHandler;
import com.greethy.user.api.rest.controller.handler.UserQueriesEndpointHandler;

@Configuration
public class EndpointRouter {

    @Bean
    @UserOpenApi
    public RouterFunction<ServerResponse> route(
            UserCommandsEndpointHandler userCommandsEndpointHandler,
            UserQueriesEndpointHandler userQueriesEndpointHandler) {
        return RouterFunctions.route()
                .path(
                        "/api/v1/user",
                        builder -> builder.nest(accept(MediaType.APPLICATION_JSON), routerBuilder -> routerBuilder
                                .POST(userCommandsEndpointHandler::registerUser)
                                .GET(
                                        "",
                                        queryParam("username-or-email", Objects::nonNull),
                                        userQueriesEndpointHandler::findUserByUsernameOrEmail)
                                .build()))
                .path(
                        "/api/v1/users",
                        builder -> builder.nest(accept(MediaType.APPLICATION_JSON), routerBuilder -> routerBuilder
                                .GET("/{user-id}", userQueriesEndpointHandler::findUserById)
                                .GET(
                                        "",
                                        queryParam("page", Objects::nonNull).or(queryParam("size", Objects::nonNull)),
                                        userQueriesEndpointHandler::findAllUserWithPagination)
                                .GET(
                                        "",
                                        accept(MediaType.APPLICATION_JSON),
                                        request -> userQueriesEndpointHandler.findAllUser())
                                .PUT("/{user-id}", userCommandsEndpointHandler::updateUser)
                                .DELETE("/{user-id}", userCommandsEndpointHandler::deleteUserPermanently)
                                .build()))
                .path(
                        "/api/v1/user-email",
                        builder -> builder.GET(
                                "/exists",
                                queryParam("email", Objects::nonNull),
                                userQueriesEndpointHandler::checkIfUserEmailExists))
                .build();
    }
}
