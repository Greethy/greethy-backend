package com.greethy.nutrition.api.rest.controller.handler;

import com.greethy.annotation.reactive.EndpointHandler;
import com.greethy.core.api.handler.ExceptionHandler;
import com.greethy.core.domain.query.CheckIfUserExistsQuery;
import com.greethy.nutrition.api.rest.dto.request.CreateMenuRequest;
import com.greethy.nutrition.core.domain.exception.NotFoundException;
import com.greethy.nutrition.core.port.in.command.CreateMenuCommand;
import lombok.RequiredArgsConstructor;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;
import org.modelmapper.ModelMapper;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

@EndpointHandler
@RequiredArgsConstructor
public class MenuCommandsEndpointHandler {

    private final ModelMapper mapper;

    private final ReactorQueryGateway queryGateway;

    private final ReactorCommandGateway commandGateway;

    private final ExceptionHandler exceptionHandler;


    public Mono<ServerResponse> createMenu(ServerRequest serverRequest) {
        String userId = serverRequest.pathVariable("user-id");
        return Mono.just(userId)
                .map(CheckIfUserExistsQuery::new)
                .flatMap(query -> queryGateway.query(query, Boolean.class)
                        .filter(Boolean::booleanValue)
                        .switchIfEmpty(Mono.error(NotFoundException::new)))
                .then(serverRequest.bodyToMono(CreateMenuRequest.class))
                .map(request -> mapper.map(request, CreateMenuCommand.class))
                .doOnNext(command -> {
                    command.setUserId(userId);
                    command.setMenuId(UUID.randomUUID().toString());
                })
                .flatMap(commandGateway::send)
                .flatMap(menuId -> ServerResponse.ok().bodyValue(menuId))

                .onErrorResume(exceptionHandler::handlingException);
    }

}
