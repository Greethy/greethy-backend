package com.greethy.nutrition.api.rest.controller;

import com.greethy.nutrition.api.rest.controller.food.FoodCommandEndpointHandler;
import com.greethy.nutrition.api.rest.controller.food.FoodQueriesEndpointHandler;
import com.greethy.nutrition.api.rest.controller.specs.BodySpecsCommandsEndpointHandler;
import com.greethy.nutrition.api.rest.controller.specs.BodySpecsQueriesEndpointHandler;
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
    public RouterFunction<ServerResponse> route(BodySpecsCommandsEndpointHandler bodySpecsCommandsHandler,
                                                BodySpecsQueriesEndpointHandler bodySpecsQueriesEndpointHandler,
                                                FoodCommandEndpointHandler foodCommandEndpointHandler,
                                                FoodQueriesEndpointHandler foodQueriesEndpointHandler) {
        return RouterFunctions.route()
                .path("/api/v1/body-specs", builder -> builder
                        .nest(accept(MediaType.APPLICATION_JSON), routerBuilder -> routerBuilder
                                .GET("{body-specs-id}", bodySpecsQueriesEndpointHandler::getBodySpecsById)
                                .GET("", queryParam("page", Objects::nonNull).or(queryParam("size", Objects::nonNull)),
                                        bodySpecsQueriesEndpointHandler::getBodySpecsPagination)
                                .GET("", request -> bodySpecsQueriesEndpointHandler.getAllBodySpecs())
                                .PUT("{body-specs-id}", bodySpecsCommandsHandler::updateBodySpecs)
                                .DELETE("{body-specs-id}", bodySpecsCommandsHandler::deleteBodySpecs)
                        )
                ).path("/api/v1/user/{user-id}", builder -> builder
                        .nest(accept(MediaType.APPLICATION_JSON), routeBuilder -> routeBuilder
                                .POST("body-specs", bodySpecsCommandsHandler::createUserBodySpecs)
                                .GET("body-specs", bodySpecsQueriesEndpointHandler::getAllUserBodySpecs)
                        )
                ).path("/api/v1/ingredients", builder -> builder
                        .nest(accept(MediaType.APPLICATION_JSON), routerBuilder -> routerBuilder
                                .PUT("{ingredient-id}", foodCommandEndpointHandler::updateIngredientById)
                                .GET("", request -> foodQueriesEndpointHandler.getAllIngredients())))

                .build();
    }

}
