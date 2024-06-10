package com.greethy.userquery.api.rest;

import com.greethy.userquery.api.rest.swagger.QuerySwagger;
import com.greethy.userquery.api.rest.handler.UserQueryHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.queryParam;

@Configuration
public class EndpointsRouter {

    @Bean
    @QuerySwagger
    RouterFunction<ServerResponse> route(UserQueryHandler userHandler) {
        return RouterFunctions.route()
                .path("api/v1/users", builder -> builder
                        .nest(accept(MediaType.APPLICATION_JSON), routerBuilder -> routerBuilder
                                .GET(userHandler::getAllByPagination)
                                .GET("{user-id}", userHandler::getUserById)
                                .GET("user",
                                        queryParam("username-or-email", StringUtils::hasText),
                                        userHandler::getByUsernameOrEmail)
                                .GET("user-email/exists", userHandler::checkIfEmailIsExisted)
                        ).build()
                ).path("api/v1/me", builder -> builder
                        .nest(accept(MediaType.APPLICATION_JSON), routerBuilder -> routerBuilder
                                .GET("", userHandler::getCurrentUser)
                        ).build()
                ).path("internal", builder -> builder
                        .GET("identity",
                                queryParam("username-or-email", StringUtils::hasText),
                                userHandler::getIdentity))
                .build();
    }


}
