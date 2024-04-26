package com.greethy.user.api.rest.controller.handler;

import com.greethy.annotation.reactive.EndpointHandler;
import com.greethy.user.api.rest.controller.ExceptionHandler;
import com.greethy.user.api.rest.dto.request.RegisterUserRequest;
import com.greethy.user.api.rest.dto.request.UpdateUserRequest;
import com.greethy.user.core.domain.exception.DuplicateUniqueFieldException;
import com.greethy.user.core.port.in.command.DeleteUserCommand;
import com.greethy.user.core.port.in.command.RegisterUserCommand;
import com.greethy.user.core.port.in.command.UpdateUserCommand;
import com.greethy.user.core.port.in.query.CheckIfUsernameOrEmailExistsQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
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
@EndpointHandler
@RequiredArgsConstructor
public class UserCommandsEndpointHandler {

    private final ModelMapper mapper;

    private final ExceptionHandler exceptionHandler;

    private final ReactorQueryGateway reactorQueryGateway;

    private final ReactorCommandGateway reactiveCommandGateway;

    private static final String PATH_VARIABLE_NAME = "user-id";

    /**
     * Registers a new user based on the provided registration request. Generates a unique user ID,
     * maps the registration request to a RegisterUserCommand, and sends the command to the CommandGateway
     * for processing.
     *
     * @param serverRequest The incoming request containing the registration details.
     * @return A Mono wrapping the ServerResponse indicating the success of user registration.
     */
    public Mono<ServerResponse> registerUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(RegisterUserRequest.class)
                .map(request -> CheckIfUsernameOrEmailExistsQuery.builder()
                        .email(request.getEmail())
                        .username(request.getUsername())
                        .build())
                .flatMap(query -> reactorQueryGateway.query(query, Boolean.class))
                .flatMap(isExisted -> {
                    if (isExisted) return Mono.error(DuplicateUniqueFieldException::new);
                    return serverRequest.bodyToMono(RegisterUserRequest.class);
                })
                .map(request -> mapper.map(request, RegisterUserCommand.class))
                .doOnNext(command -> {
                    if (!StringUtils.hasText(command.getUserId())) {
                        command.setUserId(UUID.randomUUID().toString());
                    }
                })
                .flatMap(reactiveCommandGateway::send)
                .flatMap(it -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(Map.of("user-id", it))
                ).onErrorResume(exceptionHandler::handlingException);
    }


    /**
     * Updates the profile of an existing user based on the provided update request. Extracts the
     * user ID from the request path, maps the update request to an UpdateUserCommand, and sends
     * the command to the command gateway for processing.
     *
     * @param serverRequest The incoming request containing the updated user details.
     * @return A Mono wrapping the ServerResponse indicating the success of user profile update.
     */
    public Mono<ServerResponse> updateUser(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable(PATH_VARIABLE_NAME))
                .flatMap(userId -> serverRequest.bodyToMono(UpdateUserRequest.class)
                        .map(request -> mapper.map(request, UpdateUserCommand.class))
                        .doOnNext(command -> command.setUserId(userId))
                ).flatMap(command -> reactiveCommandGateway.send(command)
                        .flatMap(it -> ServerResponse.status(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(Map.of("user-id", it)))
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
    public Mono<ServerResponse> deleteUserPermanently(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable(PATH_VARIABLE_NAME))
                .map(userId -> DeleteUserCommand.builder().userId(userId).build())
                .flatMap(command -> reactiveCommandGateway.send(command)
                        .flatMap(it -> ServerResponse.status(HttpStatus.NO_CONTENT).build())
                );
    }

}
