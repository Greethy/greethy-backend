package com.greethy.user.api.rest.controller;

import com.greethy.user.api.rest.controller.user.UserCommandsEndpointHandler;
import com.greethy.user.api.rest.controller.user.UserQueriesEndpointHandler;
import com.greethy.user.api.rest.docs.UserApiDocs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Objects;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class EndpointRouter {

    @Bean
    @UserApiDocs
    public RouterFunction<ServerResponse> route(UserCommandsEndpointHandler userCommandsEndpointHandler,
                                                UserQueriesEndpointHandler userQueriesEndpointHandler) {
        return RouterFunctions.route()
                .path("/api/v1/user", builder -> builder
                        .nest(accept(MediaType.APPLICATION_JSON), routerBuilder -> routerBuilder
                                .POST("", userCommandsEndpointHandler::registerUser)
                                .GET("/{user-id}", userQueriesEndpointHandler::findUserById)
                                .GET(queryParam("username-or-email", Objects::nonNull),
                                        userQueriesEndpointHandler::findUserByUsernameOrEmail)
                                .PUT("/{user-id}", userCommandsEndpointHandler::updateUser)
                                .DELETE("/{user-id}", userCommandsEndpointHandler::deleteUserPermanently)
                                .build()
                        )
                ).path("/api/v1/users", builder -> builder
                        .GET("", queryParam("page", Objects::nonNull)
                                        .or(queryParam("size", Objects::nonNull)),
                                userQueriesEndpointHandler::findAllUserWithPagination)
                        .GET("", accept(MediaType.APPLICATION_JSON),
                                request -> userQueriesEndpointHandler.findAllUser())
                        .build()
                ).path("/api/v1/user-email", builder -> builder
                        .GET("/exists", queryParam("email", Objects::nonNull),
                                userQueriesEndpointHandler::checkIfUserEmailExists))
                .build();
    }

}
