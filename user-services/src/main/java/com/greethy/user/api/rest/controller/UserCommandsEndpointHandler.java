package com.greethy.user.api.rest.controller;

import com.greethy.annotation.reactive.Handler;
import com.greethy.user.api.rest.dto.request.RegisterUserRequest;
import com.greethy.user.api.rest.dto.request.UpdateUserRequest;
import com.greethy.user.core.port.in.command.DeleteUserCommand;
import com.greethy.user.core.port.in.command.RegisterUserCommand;
import com.greethy.user.core.port.in.command.UpdateUserCommand;
import com.greethy.user.infra.constant.MessageConstant;

import lombok.RequiredArgsConstructor;

import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Handler
@RequiredArgsConstructor
public class UserCommandsEndpointHandler {

    private final ModelMapper mapper;

    private final ReactorCommandGateway reactorCommandGateway;

    public Mono<ServerResponse> registerUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(RegisterUserRequest.class)
                .map(request -> mapper.map(request, RegisterUserCommand.class))
                .doOnNext(command -> command.setUserId(UUID.randomUUID().toString()))
                .flatMap(command -> reactorCommandGateway.send(command)
                        .flatMap(it -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(Mono.just(MessageConstant.SUCCESS_USER_REGISTERED_MSG), String.class)
                        )
                );
    }

    public Mono<ServerResponse> updateUser(ServerRequest serverRequest) {
        String userId = serverRequest.pathVariable("user_id");
        return serverRequest.bodyToMono(UpdateUserRequest.class)
                .map(request -> mapper.map(request, UpdateUserCommand.class))
                .doOnNext(command -> command.setUserId(userId))
                .flatMap(command -> reactorCommandGateway.send(command)
                        .flatMap(it -> ServerResponse.status(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(Mono.just(MessageConstant.SUCCESS_USER_PROFILE_UPDATED_MSG), String.class)));
    }

    public Mono<ServerResponse> deleteUserPermanently(ServerRequest serverRequest) {
        String userId = serverRequest.pathVariable("user_id");
        return Mono.just(DeleteUserCommand.builder().userId(userId).build())
                .flatMap(command -> reactorCommandGateway.send(command)
                        .flatMap(it -> ServerResponse.status(HttpStatus.NO_CONTENT)
                                .body(Mono.just("Delete User successfully"), String.class)
                        )
                );
    }


}
