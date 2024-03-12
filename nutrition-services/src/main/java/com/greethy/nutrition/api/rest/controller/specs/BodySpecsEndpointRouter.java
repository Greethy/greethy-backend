package com.greethy.nutrition.api.rest.controller.specs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class BodySpecsEndpointRouter {

    @Bean
    public RouterFunction<ServerResponse> route(BodySpecsCommandsEndpointHandler bodySpecsCommandsEndpointHandler) {
        return RouterFunctions.route()
                .POST("/api/v1/user/{user_id}/body_specs",
                        accept(MediaType.APPLICATION_JSON),
                        bodySpecsCommandsEndpointHandler::createUserBodySpecs)
                .build();
    }

}
