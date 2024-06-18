package com.greethy.nutritioncommand.api.rest.handler;

import com.greethy.common.infra.component.annotation.EndpointHandler;
import com.greethy.nutritioncommand.domain.service.FoodCommandService;
import com.greethy.nutritioncommon.dto.request.command.CreateFoodCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@EndpointHandler
@RequiredArgsConstructor
public class FoodCommandHandler {

    private final FoodCommandService foodCommandService;

    public Mono<ServerResponse> createFood(ServerRequest request) {
        return request.bodyToMono(CreateFoodCommand.class)
                .flatMap(foodCommandService::createFood)
                .flatMap(response -> ServerResponse
                        .created(URI.create("/api/foods/" + response.id()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(response));
    }

    public Mono<ServerResponse> updateFood(ServerRequest request) {
        return null;
    }

    public Mono<ServerResponse> deleteFood(ServerRequest request) {
        return null;
    }

}
