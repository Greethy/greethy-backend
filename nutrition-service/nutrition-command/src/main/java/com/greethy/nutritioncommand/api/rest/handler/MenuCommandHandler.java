package com.greethy.nutritioncommand.api.rest.handler;

import com.greethy.common.api.handler.ExceptionHandler;
import com.greethy.common.infra.component.annotation.EndpointHandler;
import com.greethy.nutritioncommand.domain.service.MenuCommandService;
import com.greethy.nutritioncommon.dto.request.command.CreateMenuCommand;
import com.greethy.nutritioncommon.dto.request.command.UpdateMenuCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.security.Principal;

@EndpointHandler
@RequiredArgsConstructor
public class MenuCommandHandler {

    @Value("${client.nutrition-query.domain}")
    private String queryDomain;
    private final MenuCommandService menuService;
    private final ExceptionHandler exceptionHandler;

    public Mono<ServerResponse> createMenu(ServerRequest serverRequest) {
        return serverRequest.principal()
                .map(Principal::getName)
                .zipWith(serverRequest.bodyToMono(CreateMenuCommand.class))
                .flatMap(tuples -> menuService.createMenu(tuples.getT1(), tuples.getT2()))
                .flatMap(response -> ServerResponse
                        .created(URI.create(queryDomain + "/api/v1/menus/" + response.id()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> updateMenu(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("menu-id"))
                .zipWith(serverRequest.bodyToMono(UpdateMenuCommand.class))
                .flatMap(tuples -> menuService.updateMenu(tuples.getT1(), tuples.getT2()))
                .flatMap(response -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> deleteMenu(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("menu-id"))
                .flatMap(menuService::deleteMenu)
                .then(Mono.defer(() -> ServerResponse.noContent().build()))
                .onErrorResume(exceptionHandler::handlingException);
    }

}
