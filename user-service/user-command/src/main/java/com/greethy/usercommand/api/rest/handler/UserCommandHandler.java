package com.greethy.usercommand.api.rest.handler;

import com.greethy.common.api.handler.ExceptionHandler;
import com.greethy.common.infra.component.annotation.EndpointHandler;
import com.greethy.usercommand.domain.service.UserCommandService;
import com.greethy.usercommon.dto.request.command.UserRegistrationCommand;
import com.greethy.usercommon.dto.request.command.UpdateUserProfileCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@EndpointHandler
@RequiredArgsConstructor
public class UserCommandHandler {

    private final UserCommandService userService;

    private final ExceptionHandler exceptionHandler;

    public Mono<ServerResponse> registerGreethyUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UserRegistrationCommand.class)
                .flatMap(userService::registerUser)
                .flatMap(response -> ServerResponse.accepted()
                        .bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> registerGoogleUser(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> registerFacebookUser(ServerRequest serverRequest) {
        return null;
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
