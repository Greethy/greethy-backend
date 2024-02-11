package com.greethy.user.api.rest.controller;

import com.greethy.annotation.reactive.Handler;
import com.greethy.user.api.rest.dto.request.RegisterUserRequest;
import com.greethy.user.core.port.in.command.RegisterUserCommand;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Handler
@RequiredArgsConstructor
public class UserCommandEndpointHandler {

    private final ModelMapper mapper;

    private final CommandGateway commandGateway;

    public Mono<ServerResponse> registerUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(RegisterUserRequest.class)
                .map(request -> {
                    var command = mapper.map(request, RegisterUserCommand.class);
                    command.setUserId(UUID.randomUUID().toString());
                    return command;
                })
                .flatMap(command -> {
                    commandGateway.send(command);
                    return ServerResponse.status(HttpStatus.CREATED)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(Mono.just("User registered successfully!"), String.class);
                });
    }

}
