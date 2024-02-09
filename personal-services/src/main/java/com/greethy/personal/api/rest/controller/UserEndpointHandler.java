package com.greethy.personal.api.rest.controller;

import com.greethy.annotation.reactive.Handler;
import com.greethy.personal.api.rest.request.CreateUserRequest;
import com.greethy.personal.api.rest.response.UserResponse;
import com.greethy.personal.core.domain.entity.User;
import com.greethy.personal.core.port.inbound.CreateUserUseCase;
import com.greethy.personal.core.port.inbound.FindAllUserUseCase;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Handler
@RequiredArgsConstructor
public class UserEndpointHandler {

    private final ModelMapper mapper;

    private final CreateUserUseCase createUserUseCase;

    private final FindAllUserUseCase findAllUserUseCase;

    public Mono<ServerResponse> save(ServerRequest serverRequest){
        return serverRequest.bodyToMono(CreateUserRequest.class)
                .map(request -> mapper.map(request, User.class))
                .flatMap(user -> {
                            var response = createUserUseCase.createUser(user)
                                    .map(savedUser -> mapper.map(savedUser, UserResponse.class));
                            return ServerResponse.status(HttpStatus.CREATED)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .body(response, UserResponse.class);
                });
    }

    public Mono<ServerResponse> findAll() {
        var response = findAllUserUseCase.findAll()
                .map(user -> mapper.map(user, UserResponse.class));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response, UserResponse.class);
    }

}
