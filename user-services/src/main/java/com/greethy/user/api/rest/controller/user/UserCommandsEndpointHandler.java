package com.greethy.user.api.rest.controller.user;

import com.greethy.annotation.reactive.Handler;
import com.greethy.user.api.rest.dto.request.RegisterUserRequest;
import com.greethy.user.api.rest.dto.request.UpdateUserRequest;
import com.greethy.user.api.rest.dto.response.ErrorResponse;
import com.greethy.user.core.port.in.command.*;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.UUID;

/**
 * {@code UserCommandsEndpointHandler} is a REST controller responsible for handling user-related commands such as
 * registering (or creating), updating, deleting. This controller utilizes Spring WebFlux and Axon Framework to handle
 * reactive command processing.
 *
 * @author Kien N.Thanh
 */
@Handler
@RequiredArgsConstructor
public class UserCommandsEndpointHandler {

    private final ModelMapper mapper;

    private final ReactorCommandGateway reactiveCommandGateway;

    private static final String PATH_VARIABLE_NAME = "user_id";

    /**
     * Registers a new user based on the provided registration request. Generates a unique user ID,
     * maps the registration request to a RegisterUserCommand, and sends the command to the CommandGateway
     * for processing.
     *
     * @param serverRequest The incoming request containing the registration details.
     * @return A Mono wrapping the ServerResponse indicating the success of user registration.
     */
    Mono<ServerResponse> registerUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(RegisterUserRequest.class)
                .map(request -> mapper.map(request, RegisterUserCommand.class))
                .doOnNext(command -> command.setUserId(UUID.randomUUID().toString()))
                .flatMap(command -> reactiveCommandGateway.send(command)
                        .flatMap(it -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(Map.of("user_id", it))
                        )
                ).onErrorResume(throwable -> {
                    if (throwable instanceof CommandExecutionException exception) {
                        ErrorResponse response = exception.getDetails()
                                .map(detail -> mapper.map(detail, ErrorResponse.class))
                                .orElse(ErrorResponse.builder()
                                        .message("Something wrong with Server")
                                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                        .build()
                                );
                        return ServerResponse.status(response.getStatus())
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(response);
                    }
                    return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .build();
                });
    }

    /**
     * Updates the profile of an existing user based on the provided update request. Extracts the
     * user ID from the request path, maps the update request to an UpdateUserCommand, and sends
     * the command to the command gateway for processing.
     *
     * @param serverRequest The incoming request containing the updated user details.
     * @return A Mono wrapping the ServerResponse indicating the success of user profile update.
     */
    Mono<ServerResponse> updateUser(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable(PATH_VARIABLE_NAME))
                .flatMap(userId -> serverRequest.bodyToMono(UpdateUserRequest.class)
                        .map(request -> mapper.map(request, UpdateUserCommand.class))
                        .doOnNext(command -> command.setUserId(userId))
                ).flatMap(command -> reactiveCommandGateway.send(command)
                        .flatMap(it -> ServerResponse.status(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(Map.of("user_id", it)))
                );
    }

    /**
     * Deletes a user permanently based on the provided user ID. Extracts the
     * user ID from the request path, constructs a DeleteUserCommand, and sends
     * the command to the CommandGateway for processing.
     *
     * @param serverRequest The incoming request containing the user ID to be deleted.
     * @return A Mono wrapping the ServerResponse indicating the success of user deletion.
     */
    Mono<ServerResponse> deleteUserPermanently(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable(PATH_VARIABLE_NAME))
                .map(userId -> DeleteUserCommand.builder().userId(userId).build())
                .flatMap(command -> reactiveCommandGateway.send(command)
                        .flatMap(it -> ServerResponse.status(HttpStatus.NO_CONTENT).build())
                );
    }

}
