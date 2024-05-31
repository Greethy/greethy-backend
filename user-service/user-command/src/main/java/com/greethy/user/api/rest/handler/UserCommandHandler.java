package com.greethy.user.api.rest.handler;

import com.greethy.annotation.reactive.EndpointHandler;
import com.greethy.core.api.handler.ExceptionHandler;
import com.greethy.user.domain.service.UserService;
import com.greethy.user.common.dto.request.command.RegisterUserCommand;
import com.greethy.user.common.dto.request.command.UpdateUserProfileCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@EndpointHandler
@RequiredArgsConstructor
public class UserCommandHandler {

    private final UserService userService;

    private final ExceptionHandler exceptionHandler;

    public Mono<ServerResponse> registerUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(RegisterUserCommand.class)
                .flatMap(userService::registerUser)
                .flatMap(response -> ServerResponse.status(HttpStatus.CREATED)
                        .bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> updateUser(ServerRequest serverRequest) {
        String userId = serverRequest.pathVariable("user-id");
        return serverRequest.bodyToMono(UpdateUserProfileCommand.class)
                .doOnNext(command -> command.setUserId(userId))
                .flatMap(userService::updateUserProfile)
                .flatMap(response -> ServerResponse.ok().bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> deleteUserTemporary(ServerRequest serverRequest) {
        return null;
    }

}
