package com.greethy.nutrition.api.rest.controller.food;

import com.greethy.annotation.reactive.EndpointHandler;
import lombok.RequiredArgsConstructor;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@EndpointHandler
@RequiredArgsConstructor
public class FoodCommandEndpointHandler {

    private final ReactorCommandGateway commandGateway;

    public Mono<ServerResponse> updateIngredientById(ServerRequest request) {
//        return Mono.just(request.pathVariable("ingredient-id"))
//                .;
        return null;
    }

}
