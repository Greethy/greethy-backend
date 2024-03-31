package com.greethy.nutrition.api.rest.controller.specs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Objects;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class BodySpecsEndpointRouter {

    @Bean
    public RouterFunction<ServerResponse> route(BodySpecsCommandsEndpointHandler bodySpecsCommandsEndpointHandler,
                                                BodySpecsQueriesEndpointHandler bodySpecsQueriesEndpointHandler) {
        return RouterFunctions.route()
                .path("/api/v1/body-specs", builder -> builder
                        .nest(accept(MediaType.APPLICATION_JSON), routerBuilder -> routerBuilder
                                .GET("{body-specs-id}", bodySpecsQueriesEndpointHandler::getBodySpecsById)
                                .GET("", queryParam("page", Objects::nonNull).or(queryParam("size", Objects::nonNull)),
                                        bodySpecsQueriesEndpointHandler::getBodySpecsPagination)
                                .GET("", request -> bodySpecsQueriesEndpointHandler.getAllBodySpecs())
                                .PUT("{body-specs-id}", bodySpecsCommandsEndpointHandler::updateBodySpecs)
                                .DELETE("{body-specs-id}", bodySpecsCommandsEndpointHandler::deleteBodySpecs)
                        )
                ).path("/api/v1/user/{user-id}", builder -> builder
                        .nest(accept(MediaType.APPLICATION_JSON), routeBuilder -> routeBuilder
                                .POST("body-specs", bodySpecsCommandsEndpointHandler::createUserBodySpecs)
                                .GET("body-specs", bodySpecsQueriesEndpointHandler::getAllUserBodySpecs)
                        )
                ).build();
    }

}
