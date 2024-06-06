package com.greethy.usercommand.api.rest.handler;

import com.greethy.common.api.handler.ExceptionHandler;
import com.greethy.common.infra.component.annotation.EndpointHandler;
import com.greethy.usercommand.domain.service.AuthCommandService;
import com.greethy.usercommon.dto.request.command.RegisterUserCommand;
import com.greethy.usercommon.dto.request.command.UserLoginCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@EndpointHandler
@RequiredArgsConstructor
public class AuthCommandHandler {

    private final ExceptionHandler exceptionHandler;

    private final AuthCommandService authService;

    public Mono<ServerResponse> authenticate(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UserLoginCommand.class)
                .flatMap(authService::authenticate)
                .flatMap(response -> ServerResponse.ok().bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> registerGreethyUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(RegisterUserCommand.class)
                .flatMap(authService::registerGreethyUser)
                .flatMap(response -> ServerResponse.ok().bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);
    }

}
