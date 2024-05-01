package com.greethy.nutrition.api.rest.controller.handler;

import java.util.UUID;

import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.greethy.annotation.reactive.EndpointHandler;
import com.greethy.core.api.handler.ExceptionHandler;
import com.greethy.core.domain.query.CheckIfUserExistsQuery;
import com.greethy.nutrition.api.rest.dto.request.CreateBodySpecsRequest;
import com.greethy.nutrition.api.rest.dto.request.UpdateBodySpecsRequest;
import com.greethy.nutrition.api.rest.dto.response.BodySpecsCreatedResponse;
import com.greethy.nutrition.core.domain.exception.NotFoundException;
import com.greethy.nutrition.core.port.in.command.*;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@EndpointHandler
@RequiredArgsConstructor
public class BodySpecsCommandsEndpointHandler {

    private final ModelMapper mapper;

    private final ReactorQueryGateway queryGateway;

    private final ReactorCommandGateway commandGateway;

    private final ExceptionHandler exceptionHandler;

    public Mono<ServerResponse> createUserBodySpecs(ServerRequest serverRequest) {
        var userId = serverRequest.pathVariable("user-id");
        return Mono.just(userId)
                .map(CheckIfUserExistsQuery::new)
                .flatMap(query -> queryGateway
                        .query(query, Boolean.class)
                        .filter(Boolean::booleanValue)
                        .switchIfEmpty(Mono.error(NotFoundException::new)))
                .then(serverRequest.bodyToMono(CreateBodySpecsRequest.class))
                .map(request -> mapper.map(request, CreateBodySpecsCommand.class))
                .doOnNext(command -> {
                    command.setUserId(userId);
                    command.setBodySpecsId(UUID.randomUUID().toString());
                })
                .flatMap(commandGateway::send)
                .map(BodySpecsCreatedResponse::new)
                .flatMap(response -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> updateBodySpecs(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("body-specs-id"))
                .flatMap(bodySpecsId -> serverRequest
                        .bodyToMono(UpdateBodySpecsRequest.class)
                        .map(request -> mapper.map(request, UpdateBodySpecsCommand.class))
                        .doOnNext(command -> command.setBodySpecsId(bodySpecsId)))
                .flatMap(commandGateway::send)
                .map(BodySpecsCreatedResponse::new)
                .flatMap(response -> ServerResponse.status(HttpStatus.CREATED).bodyValue(response));
    }

    public Mono<ServerResponse> deleteBodySpecs(ServerRequest serverRequest) {
        return Mono.just(DeleteBodySpecsCommand.builder()
                        .bodySpecsId(serverRequest.pathVariable("body-specs-id"))
                        .userId(serverRequest.pathVariable("user-id"))
                        .build())
                .flatMap(commandGateway::send)
                .flatMap(it -> ServerResponse.noContent().build());
    }
}
