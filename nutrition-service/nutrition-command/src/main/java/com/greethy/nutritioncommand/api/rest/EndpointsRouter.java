package com.greethy.nutritioncommand.api.rest;

import com.greethy.nutritioncommand.api.rest.handler.BodySpecCommandHandler;
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
    RouterFunction<ServerResponse> route(BodySpecCommandHandler bodySpecHandler) {
        return RouterFunctions.route()
                .path("/api/v1/body-specs", routerBuilder1 -> routerBuilder1
                        .nest(accept(MediaType.APPLICATION_JSON), routerBuilder2 -> routerBuilder2
                                .POST("", bodySpecHandler::createUserBodySpec)))
                .build();
    }

}
