package com.greethy.nutritioncommand.api.rest;

import com.greethy.nutritioncommand.api.rest.handler.BodySpecCommandHandler;
import com.greethy.nutritioncommand.api.rest.handler.FoodCommandHandler;
import com.greethy.nutritioncommand.api.rest.handler.MenuCommandHandler;
import com.greethy.nutritioncommand.api.rest.handler.ScheduleCommandHandler;
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
    RouterFunction<ServerResponse> route(FoodCommandHandler foodHandler,
                                         MenuCommandHandler menuHandler,
                                         BodySpecCommandHandler bodySpecHandler,
                                         ScheduleCommandHandler scheduleHandler) {
        return RouterFunctions.route()
                .path("api/v1", builder -> builder
                        .nest(accept(MediaType.APPLICATION_JSON), routerBuilder -> routerBuilder
                                .POST("body-specs", bodySpecHandler::createUserBodySpec)
                                .PUT("body-specs/{body-spec-id}", bodySpecHandler::updateBodySpec)
                                .DELETE("body-specs/{body-spec-id}", bodySpecHandler::deleteBodySpec)

                                .POST("foods", foodHandler::createFood)
                                .PUT("foods/{food-id}", foodHandler::updateFood)
                                .DELETE("foods/{food-id}", foodHandler::deleteFood)

                                .POST("menus", menuHandler::createMenu)
                                .PUT("menus/{menu-id}", menuHandler::updateMenu)
                                .DELETE("menus/{menu-id}", menuHandler::deleteMenu)

                                .POST("schedules", scheduleHandler::createSchedule)

                        )).build();
    }

}
