package com.greethy.nutrition.api.rest.controller;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

import java.util.Objects;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.greethy.nutrition.api.rest.controller.handler.BodySpecsCommandsEndpointHandler;
import com.greethy.nutrition.api.rest.controller.handler.BodySpecsQueriesEndpointHandler;
import com.greethy.nutrition.api.rest.controller.handler.FoodCommandEndpointHandler;
import com.greethy.nutrition.api.rest.controller.handler.FoodQueriesEndpointHandler;
import com.greethy.nutrition.api.rest.controller.handler.IngredientQueriesHandler;
import com.greethy.nutrition.api.rest.docs.BodySpecsApiDocument;

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
    @BodySpecsApiDocument
    public RouterFunction<ServerResponse> route() {
        return RouterFunctions.route()
                .path(
                        "/api/v1/users/{user-id}",
                        builder -> builder.nest(accept(MediaType.APPLICATION_JSON), routeBuilder -> routeBuilder
                                .POST("body-specs", bodySpecsCommandsHandler::createUserBodySpecs)
                                .GET(
                                        "body-specs",
                                        queryParam("offset", Objects::nonNull)
                                                .or(queryParam("limit", Objects::nonNull)),
                                        bodySpecsQueriesEndpointHandler::getUserBodySpecsPagination)
                                .GET("body-specs", bodySpecsQueriesEndpointHandler::getAllUserBodySpecs)
                                .POST("food", foodCommandEndpointHandler::createFood)
                                .DELETE("body-specs/{body-specs-id}", bodySpecsCommandsHandler::deleteBodySpecs)))
                .path(
                        "/api/v1/body-specs",
                        builder -> builder.nest(accept(MediaType.APPLICATION_JSON), routerBuilder -> routerBuilder
                                .GET("{body-specs-id}", bodySpecsQueriesEndpointHandler::getBodySpecsById)
                                .GET(
                                        queryParam("offset", Objects::nonNull)
                                                .or(queryParam("limit", Objects::nonNull)),
                                        bodySpecsQueriesEndpointHandler::getBodySpecsPagination)
                                .GET("", request -> bodySpecsQueriesEndpointHandler.getAllBodySpecs())
                                .PUT("{body-specs-id}", bodySpecsCommandsHandler::updateBodySpecs)))
                .path(
                        "/api/v1/foods",
                        builder -> builder.nest(accept(MediaType.APPLICATION_JSON), routerBuilder -> routerBuilder
                                .GET("", foodQueriesEndpointHandler::getFoodWithPagination)
                                .PUT("{food-id}/ingredients", foodCommandEndpointHandler::addIngredientsToFood)))
                .path(
                        "/api/v1/ingredients",
                        builder -> builder.nest(accept(MediaType.APPLICATION_JSON), routerBuilder -> routerBuilder
                                .GET("{ingredient-id}", ingredientQueriesHandler::getIngredientById)
                                .GET(
                                        queryParam("offset", Objects::nonNull)
                                                .or(queryParam("limit", Objects::nonNull)),
                                        ingredientQueriesHandler::getIngredientsWithPagination)
                                .GET("", request -> ingredientQueriesHandler.getAllIngredients())))
                .build();
    }
}
