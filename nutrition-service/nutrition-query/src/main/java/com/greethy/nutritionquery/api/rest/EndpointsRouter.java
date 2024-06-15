package com.greethy.nutritionquery.api.rest;

import com.greethy.nutritionquery.api.rest.handler.BodySpecQueryHandler;
import com.greethy.nutritionquery.api.rest.handler.FoodQueryHandler;
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
    public RouterFunction<ServerResponse> route(BodySpecQueryHandler bodySpecHandler, FoodQueryHandler foodHandler) {
        return RouterFunctions.route()
                .path("/api/v1/body-specs", builder -> builder
                        .nest(accept(MediaType.APPLICATION_JSON), routerBuilder -> routerBuilder
                                .GET("{body-spec-id}", bodySpecHandler::getBodySpecById))
                ).path("internal", builder -> builder
                        .GET("foods/ids", request -> foodHandler.getAllFoodIds()))
                .build();
    }

}
