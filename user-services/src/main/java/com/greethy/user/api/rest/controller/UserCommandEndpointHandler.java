package com.greethy.user.api.rest.controller;

import com.greethy.annotation.reactive.Handler;
import com.greethy.user.api.error.UserBusinessError;
import com.greethy.user.api.rest.dto.request.RegisterUserRequest;
import com.greethy.user.api.rest.dto.request.UpdateUserProfileRequest;
import com.greethy.user.core.domain.exception.DuplicateUniqueFieldException;
import com.greethy.user.core.port.in.command.RegisterUserCommand;
import com.greethy.user.core.port.in.command.UpdateUserProfileCommand;
import com.greethy.user.core.port.out.CheckIfExistsUserPort;
import com.greethy.user.infrastructure.constant.MessageConstant;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.UUID;

@Handler
@RequiredArgsConstructor
public class UserCommandEndpointHandler {

    private final ModelMapper mapper;

    private final CheckIfExistsUserPort checkIfExistsUserPort;

    private final ReactorCommandGateway reactorCommandGateway;

    public Mono<ServerResponse> registerUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(RegisterUserRequest.class)
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(request -> {
                    Boolean isExisted = checkIfExistsUserPort.existsByUsernameOrEmail(request.getUsername(), request.getEmail()).block();
                    if(Boolean.TRUE.equals(isExisted)) {
                        throw new DuplicateUniqueFieldException();
                    }
                })
                .map(request -> mapper.map(request, RegisterUserCommand.class))
                .doOnNext(command -> command.setUserId(UUID.randomUUID().toString()))
                .flatMap(command -> reactorCommandGateway.send(command)
                        .flatMap(it -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(Mono.just(MessageConstant.SUCCESS_USER_REGISTERED_MSG), String.class))
                );

    }

    public Mono<ServerResponse> updateUserProfile(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UpdateUserProfileRequest.class)
                .map(request -> mapper.map(request, UpdateUserProfileCommand.class))
                .flatMap(command -> {
                    reactorCommandGateway.send(command).subscribe();
                    return ServerResponse.status(HttpStatus.OK)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(Mono.just(MessageConstant.SUCCESS_USER_PROFILE_UPDATED_MSG), String.class);
                });
    }

    private String getErrorResponseMessage(Throwable throwable) {
        if (throwable instanceof CommandExecutionException commandExecutionException) {
            return commandExecutionException.getDetails()
                    .map(it -> {
                        UserBusinessError userBusinessError = (UserBusinessError) it;
                        return userBusinessError.message() + " : ";
                    }).orElseGet(commandExecutionException::getMessage);
        } else {
            return throwable.getMessage();
        }
    }

}
