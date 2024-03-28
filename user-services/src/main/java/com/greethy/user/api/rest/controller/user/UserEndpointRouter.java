package com.greethy.user.api.rest.controller.user;

import com.greethy.user.api.rest.docs.UserApiDocs;
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
    @UserApiDocs
    public RouterFunction<ServerResponse> route(UserCommandsEndpointHandler userCommandEndpointHandler,
                                                UserQueriesEndpointHandler userQueriesEndpointHandler) {
        return RouterFunctions.route()
                .path("/api/v1/user", builder -> builder
                        .POST(accept(MediaType.APPLICATION_JSON),
                                userCommandEndpointHandler::registerUser)
                        .GET("/{user-id}",
                                accept(MediaType.APPLICATION_JSON),
                                userQueriesEndpointHandler::findUserById)
                        .GET(queryParam("username-or-email", t -> true),
                                userQueriesEndpointHandler::findUserByUsernameOrEmail)
                        .PUT("/{user-id}",
                                accept(MediaType.APPLICATION_JSON),
                                userCommandEndpointHandler::updateUser)
                        .DELETE("/{user-id}",
                                accept(MediaType.APPLICATION_JSON),
                                userCommandEndpointHandler::deleteUserPermanently)
                        .build())
                .path("/api/v1/users", builder -> builder
                        .GET(accept(MediaType.APPLICATION_JSON),
                                request -> userQueriesEndpointHandler.findAllUser())
                        .GET("/page",queryParam("page", t -> true).and(queryParam("size", t -> true)),
                                userQueriesEndpointHandler::findAllUserWithPageable)
                        .build())
                .path("/api/v1/user-email", builder -> builder
                        .GET("/exists",
                                queryParam("email", t -> true),
                                userQueriesEndpointHandler::checkIfUserEmailExists))
                .build();
    }

}
