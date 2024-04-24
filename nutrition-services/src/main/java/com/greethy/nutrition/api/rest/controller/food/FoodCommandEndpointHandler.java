package com.greethy.nutrition.api.rest.controller.food;

import com.greethy.annotation.reactive.EndpointHandler;
import com.greethy.nutrition.api.rest.dto.request.AddIngredientsToFoodRequest;
import com.greethy.nutrition.api.rest.dto.request.CreateFoodRequest;
import com.greethy.nutrition.api.rest.dto.response.FoodCreatedResponse;
import com.greethy.nutrition.core.port.in.command.AddIngredientsToFoodCommand;
import com.greethy.nutrition.core.port.in.command.CreateFoodCommand;
import lombok.RequiredArgsConstructor;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

@EndpointHandler
@RequiredArgsConstructor
public class FoodCommandEndpointHandler {

    private final ModelMapper mapper;

    private final ReactorCommandGateway commandGateway;

    public Mono<ServerResponse> createFood(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CreateFoodRequest.class)
                .map(request -> mapper.map(request, CreateFoodCommand.class))
                .doOnNext(command -> {
                    command.setUserId(serverRequest.pathVariable("user-id"));
                    command.setFoodId(UUID.randomUUID().toString());
                })
                .flatMap(commandGateway::send)
                .map(FoodCreatedResponse::new)
                .flatMap(response -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(response)
                );
    }

    public Mono<ServerResponse> addIngredientsToFood(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(AddIngredientsToFoodRequest.class)
                .doOnNext(request -> request.getIngredients().forEach(System.out::println))
                .map(request -> mapper.map(request, AddIngredientsToFoodCommand.class))
                .doOnNext(command -> command.setFoodId(serverRequest.pathVariable("food-id")))
                .flatMap(commandGateway::send)
                .flatMap(it -> ServerResponse.ok().bodyValue(it));

    }

}
