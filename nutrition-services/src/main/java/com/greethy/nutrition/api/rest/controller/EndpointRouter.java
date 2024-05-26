package com.greethy.nutrition.api.rest.controller;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

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

    private final MenuCommandsEndpointHandler menuCommandsHandler;

    private final UserEndpointHandler userEndpointHandler;

    @Bean
    @NutritionOpenApi
    public RouterFunction<ServerResponse> route() {
        return RouterFunctions.route()
                .path("/api/v1/users/{user-id}", builder -> builder
                        .GET("", userEndpointHandler::getUser)
                        .nest(accept(MediaType.APPLICATION_JSON), routeBuilder -> routeBuilder
                                .POST("body-specs", bodySpecsCommandsHandler::createUserBodySpecs)
                                .GET("body-specs", bodySpecsQueriesEndpointHandler::getUserBodySpecsWithPagination)
                                .POST("food", foodCommandEndpointHandler::createFood)
                                .POST("menu", menuCommandsHandler::createMenu)
                                .DELETE("body-specs/{body-specs-id}", bodySpecsCommandsHandler::deleteBodySpecs))
                ).path("/api/v1/body-specs", builder -> builder
                        .nest(accept(MediaType.APPLICATION_JSON), routerBuilder -> routerBuilder
                                .GET("{body-specs-id}", bodySpecsQueriesEndpointHandler::getBodySpecsById)
                                .PUT("{body-specs-id}", bodySpecsCommandsHandler::updateBodySpecs)
                                .GET("", bodySpecsQueriesEndpointHandler::getBodySpecsPagination))
                ).path("/api/v1/menus", builder -> builder
                        .nest(accept(MediaType.APPLICATION_JSON), routerBuilder -> routerBuilder
                                .POST("/default", menuCommandsHandler::createMenu))
                ).path("/api/v1/foods", builder -> builder
                        .GET("all", request -> foodQueriesEndpointHandler.getAllFood())
                        .nest(accept(MediaType.APPLICATION_JSON), routerBuilder -> routerBuilder
                                .GET("{food-id}", foodQueriesEndpointHandler::getFoodById)
                                .GET("", foodQueriesEndpointHandler::getFoodWithPagination)
                                .PUT("{food-id}/ingredients", foodCommandEndpointHandler::addIngredientsToFood))
                ).path("/api/v1/ingredients", builder -> builder
                        .nest(accept(MediaType.APPLICATION_JSON), routerBuilder -> routerBuilder
                                .GET("{ingredient-id}", ingredientQueriesHandler::getIngredientById)
                                .GET("", ingredientQueriesHandler::getIngredientsWithPagination))
                ).build();
    }
}
