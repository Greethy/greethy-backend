package com.greethy.user.api.rest.controller.user;

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

import java.util.Map;
import java.util.UUID;

/**
 * {@code UserCommandsEndpointHandler} is a REST controller responsible for handling user-related commands such as
 * registering (or creating), updating, deleting. This controller utilizes Spring WebFlux and Axon Framework to handle
 * reactive command processing.
 *
 * @author KienThanh
 */
@Handler
@RequiredArgsConstructor
public class UserCommandsEndpointHandler {

    private final ModelMapper mapper;

    private final ReactorCommandGateway reactorCommandGateway;

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
                .flatMap(command -> reactorCommandGateway.send(command)
                        .flatMap(it -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(Map.of("user_id", it))
                        )
                );
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
        String userId = serverRequest.pathVariable("user_id");
        return serverRequest.bodyToMono(UpdateUserRequest.class)
                .map(request -> mapper.map(request, UpdateUserCommand.class))
                .doOnNext(command -> command.setUserId(userId))
                .flatMap(command -> reactorCommandGateway.send(command)
                        .flatMap(it -> ServerResponse.status(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(Mono.just(MessageConstant.SUCCESS_USER_PROFILE_UPDATED_MSG), String.class)));
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
        String userId = serverRequest.pathVariable("user_id");
        return Mono.just(DeleteUserCommand.builder().userId(userId).build())
                .flatMap(command -> reactorCommandGateway.send(command)
                        .flatMap(it -> ServerResponse.status(HttpStatus.NO_CONTENT)
                                .body(Mono.just("Delete User successfully"), String.class)
                        )
                );
    }


}
