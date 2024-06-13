package com.greethy.nutritioncommand.api.rest;

import com.greethy.nutritioncommand.api.rest.handler.BodySpecCommandHandler;
import com.greethy.nutritioncommand.api.rest.handler.FoodCommandHandler;
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
    RouterFunction<ServerResponse> route(BodySpecCommandHandler bodySpecHandler,
                                         FoodCommandHandler foodHandler) {
        return RouterFunctions.route()
                .path("/api/v1/body-specs", builder -> builder
                        .nest(accept(MediaType.APPLICATION_JSON), routerBuilder2 -> routerBuilder2
                                .POST("", bodySpecHandler::createUserBodySpec)
                                .PUT("/{body-spec-id}", bodySpecHandler::updateUserBodySpec)
                        )
                ).path("/api/v1/ingredients", builder -> builder
                        .nest(accept(MediaType.APPLICATION_JSON), routerBuilder -> routerBuilder
                                .POST("", request -> ServerResponse.accepted().build()))
                ).path("/api/v1/foods", builder -> builder
                        .nest(accept(MediaType.APPLICATION_JSON), routerBuilder -> routerBuilder
                                .POST("", foodHandler::createFood)))
                .build();
    }

}
