package com.greethy.nutrition.api.rest.controller;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.queryParam;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.greethy.nutrition.api.rest.controller.handler.*;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class EndpointRouter {

    private final BodySpecsCommandsEndpointHandler bodySpecsCommandsHandler;

    private final BodySpecsQueriesEndpointHandler bodySpecsQueriesEndpointHandler;

    private final FoodCommandEndpointHandler foodCommandEndpointHandler;

    private final FoodQueriesEndpointHandler foodQueriesEndpointHandler;

    private final IngredientQueriesHandler ingredientQueriesHandler;

    @Bean
    @NutritionOpenApi
    public RouterFunction<ServerResponse> route() {
        return RouterFunctions.route()
                .path(
                        "/api/v1/users/{user-id}",
                        builder -> builder.nest(accept(MediaType.APPLICATION_JSON), routeBuilder -> routeBuilder
                                .POST("body-specs", bodySpecsCommandsHandler::createUserBodySpecs)
                                .GET("body-specs", bodySpecsQueriesEndpointHandler::getUserBodySpecsWithPagination)
                                .GET("body-specs", bodySpecsQueriesEndpointHandler::getAllUserBodySpecs)
                                .POST("food", foodCommandEndpointHandler::createFood)
                                .DELETE("body-specs/{body-specs-id}", bodySpecsCommandsHandler::deleteBodySpecs)))
                .path(
                        "/api/v1/body-specs",
                        builder -> builder.nest(accept(MediaType.APPLICATION_JSON), routerBuilder -> routerBuilder
                                .GET("{body-specs-id}", bodySpecsQueriesEndpointHandler::getBodySpecsById)
                                .PUT("{body-specs-id}", bodySpecsCommandsHandler::updateBodySpecs)
                                .GET("", bodySpecsQueriesEndpointHandler::getBodySpecsPagination)))
                .path(
                        "/api/v1/foods",
                        builder -> builder.nest(accept(MediaType.APPLICATION_JSON), routerBuilder -> routerBuilder
                                .GET("{food-id}", foodQueriesEndpointHandler::getFoodById)
                                .GET(
                                        queryParam("offset", "0").and(queryParam("limit", "20")),
                                        foodQueriesEndpointHandler::getFoodWithPagination)
                                .PUT("{food-id}/ingredients", foodCommandEndpointHandler::addIngredientsToFood)))
                .path(
                        "/api/v1/ingredients",
                        builder -> builder.nest(accept(MediaType.APPLICATION_JSON), routerBuilder -> routerBuilder
                                .GET("{ingredient-id}", ingredientQueriesHandler::getIngredientById)
                                .GET("",
                                        queryParam("offset", "0").and(queryParam("limit", "20")),
                                        ingredientQueriesHandler::getIngredientsWithPagination)
                        ))
                .build();
    }
}
