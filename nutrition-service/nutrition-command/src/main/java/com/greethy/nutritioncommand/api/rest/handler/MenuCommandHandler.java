package com.greethy.nutritioncommand.api.rest.handler;

import com.greethy.common.api.handler.ExceptionHandler;
import com.greethy.common.infra.component.annotation.EndpointHandler;
import com.greethy.nutritioncommand.domain.service.MenuCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.security.Principal;

@EndpointHandler
@RequiredArgsConstructor
public class MenuCommandHandler {

    private final ExceptionHandler exceptionHandler;

    private final MenuCommandService menuCommandService;

    public Mono<ServerResponse> createArrangedMenu(ServerRequest serverRequest) {
        return serverRequest.principal()
                .map(Principal::getName)
                .flatMap(menuCommandService::createArrangedMenu)
                .flatMap(response -> ServerResponse
                        .created(URI.create("/api/v1/menu" + response.id()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> createManualMenu(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> updateMenu(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> deleteMenu(ServerRequest serverRequest) {
        return null;
    }

}
