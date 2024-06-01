package com.greethy.userquery.api.rest;

import com.greethy.usercommon.annotation.QuerySwagger;
import com.greethy.userquery.api.rest.handler.UserQueryHandler;
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
    @QuerySwagger
    RouterFunction<ServerResponse> route(UserQueryHandler queryHandler) {
        return RouterFunctions.route()
                .path("/users", routerBuilder1 -> routerBuilder1
                        .nest(accept(MediaType.APPLICATION_JSON), routerBuilder2 -> routerBuilder2
                                .GET("", queryHandler::getAllByPagination)
                                .GET("{user-id}", queryHandler::getUserById)
                                .GET("user", queryHandler::getByUsernameOrEmail)
                                .GET("user-email/exists", queryHandler::checkIfEmailIsExisted)
                        ).build()
                ).build();
    }


}
