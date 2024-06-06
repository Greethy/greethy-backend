package com.greethy.usercommand.api.rest.handler;

import com.greethy.common.api.handler.ExceptionHandler;
import com.greethy.common.infra.component.annotation.EndpointHandler;
import com.greethy.usercommand.domain.service.UserCommandService;
import com.greethy.usercommon.dto.request.command.RegisterUserCommand;
import com.greethy.usercommon.dto.request.command.UpdateUserProfileCommand;
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

    private final UserCommandService userService;

    private final ExceptionHandler exceptionHandler;

    public Mono<ServerResponse> registerUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(RegisterUserCommand.class)
                .flatMap(userService::registerUser)
                .flatMap(response -> ServerResponse.status(HttpStatus.CREATED)
                        .bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> updateUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UpdateUserProfileCommand.class)
                .zipWith(Mono.fromSupplier(() -> serverRequest.pathVariable("user-id")))
                .flatMap(tuple -> userService.updateUserProfile(tuple.getT2(), tuple.getT1()))
                .flatMap(response -> ServerResponse.ok().bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> deleteUserTemporary(ServerRequest serverRequest) {
        return null;
    }

}
