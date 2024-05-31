package com.greethy.user.api.rest;

import com.greethy.user.api.rest.handler.UserQueryHandler;
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
    RouterFunction<ServerResponse> route(UserQueryHandler queryHandler) {
        return RouterFunctions.route()
                .path("/users", builder -> builder
                        .nest(accept(MediaType.APPLICATION_JSON), routerBuilder -> routerBuilder
                                .GET("{user-id}", queryHandler::getUserById)
                        ).build()
                ).build();
    }


}
