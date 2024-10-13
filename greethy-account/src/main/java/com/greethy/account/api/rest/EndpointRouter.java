package com.greethy.account.api.rest;

import com.greethy.account.api.rest.handler.AccountEndpointHandler;
import com.greethy.account.api.rest.handler.AuthEndpointHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
@RequiredArgsConstructor
public class EndpointRouter {

    private final AuthEndpointHandler authEndpointHandler;
    private final AccountEndpointHandler accountEndpointHandler;

    @Bean
    public RouterFunction<ServerResponse> route() {
        return RouterFunctions.route()
                .path("/v0/auth", builder -> builder
                        .POST("/login", accept(MediaType.APPLICATION_JSON), authEndpointHandler::login)
                        .POST("/signup", accept(MediaType.APPLICATION_JSON), authEndpointHandler::signup)
                        .POST("/logout", accept(MediaType.APPLICATION_JSON), authEndpointHandler::logout))
                .path("/v0/me", builder -> builder
                        .GET("", accountEndpointHandler::getCurrentAccount)
                        .build())

                .build();
    }

}
