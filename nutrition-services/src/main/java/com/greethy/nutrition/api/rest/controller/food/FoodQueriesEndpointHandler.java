package com.greethy.nutrition.api.rest.controller.food;

import com.greethy.annotation.reactive.EndpointHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@EndpointHandler
@RequiredArgsConstructor
public class FoodQueriesEndpointHandler {

    public Mono<ServerResponse> getFoodWithPagination(ServerRequest serverRequest) {
        return null;
    }

}
