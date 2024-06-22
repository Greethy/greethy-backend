package com.greethy.nutritionquery.api.rest;

import com.greethy.nutritionquery.api.rest.handler.BodySpecQueryHandler;
import com.greethy.nutritionquery.api.rest.handler.FoodQueryHandler;
import com.greethy.nutritionquery.api.rest.handler.MenuQueryHandler;
import com.greethy.nutritionquery.api.rest.handler.RecommendQueryHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class EndpointsRouter {

    @Bean
    public RouterFunction<ServerResponse> route(FoodQueryHandler foodHandler,
                                                MenuQueryHandler menuHandler,
                                                BodySpecQueryHandler bodySpecHandler,
                                                RecommendQueryHandler recommendHandler) {
        return RouterFunctions.route()
                .path("api/v1", builder -> builder
                        .nest(accept(MediaType.APPLICATION_JSON), routerBuilder -> routerBuilder
                                .GET("body-specs", bodySpecHandler::getBodySpecsPagination)
                                .GET("me/body-specs", bodySpecHandler::getUserBodySpecsPagination)
                                .GET("body-specs/{body-spec-id}", bodySpecHandler::getBodySpecById)

                                .GET("foods",
                                        queryParam("offset", StringUtils::hasText)
                                                .or(queryParam("limit", StringUtils::hasText))
                                                .or(queryParam("sort", StringUtils::hasText)),
                                        foodHandler::getFoodsPagination)
                                .GET("foods/search",
                                        queryParam("name", StringUtils::hasText)
                                                .or(queryParam("offset", StringUtils::hasText))
                                                .or(queryParam("limit", StringUtils::hasText))
                                                .or(queryParam("sort", StringUtils::hasText)),
                                        foodHandler::searchByFoodName)
                                .GET("foods/{food-id}", foodHandler::getFoodById)

                                .GET("menus/{menu-id}", menuHandler::getMenuById)
                                .GET("menus/suggest/foods", menuHandler::suggestMenuFoods)

                                .GET("recommend/me/foods", recommendHandler::getMeFoodRecommendations)
                        )
                ).path("internal", builder -> builder
                        .GET("foods/ids", request -> foodHandler.getAllFoodIds()))
                .build();
    }

}
