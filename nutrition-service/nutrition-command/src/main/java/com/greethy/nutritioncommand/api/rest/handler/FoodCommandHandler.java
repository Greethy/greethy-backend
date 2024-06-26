package com.greethy.nutritioncommand.api.rest.handler;

import com.greethy.common.api.handler.ExceptionHandler;
import com.greethy.common.infra.component.annotation.EndpointHandler;
import com.greethy.nutritioncommand.domain.service.FoodCommandService;
import com.greethy.nutritioncommon.dto.request.command.CreateFoodCommand;
import com.greethy.nutritioncommon.dto.request.command.UpdateFoodCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@EndpointHandler
@RequiredArgsConstructor
public class FoodCommandHandler {

    private final FoodCommandService foodService;
    private final ExceptionHandler exceptionHandler;

    public Mono<ServerResponse> createFood(ServerRequest request) {
        return request.bodyToMono(CreateFoodCommand.class)
                .flatMap(foodService::createFood)
                .flatMap(response -> ServerResponse
                        .created(URI.create("/api/foods/" + response.id()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(response));
    }

    public Mono<ServerResponse> updateFood(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("food-id"))
                .zipWith(serverRequest.bodyToMono(UpdateFoodCommand.class))
                .flatMap(tuple2 -> foodService.updateFood(tuple2.getT1(), tuple2.getT2()))
                .flatMap(response -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> deleteFood(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("food-id"))
                .flatMap(foodService::deleteFood)
                .then(Mono.defer(() -> ServerResponse.noContent().build()))
                .onErrorResume(exceptionHandler::handlingException);
    }

}
