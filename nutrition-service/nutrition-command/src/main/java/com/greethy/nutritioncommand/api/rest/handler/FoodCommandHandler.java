package com.greethy.nutritioncommand.api.rest.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class FoodCommandHandler {

    public Mono<ServerResponse> createFood(ServerRequest request) {
        return null;
    }

}
