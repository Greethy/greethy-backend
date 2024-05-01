package com.greethy.nutrition.api.rest.controller.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.greethy.annotation.reactive.EndpointHandler;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@EndpointHandler
@RequiredArgsConstructor
public class FoodQueriesEndpointHandler {

    public Mono<ServerResponse> getFoodWithPagination(ServerRequest serverRequest) {
        return null;
    }
}
