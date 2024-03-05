package com.greethy.nutrition.api.rest.controller.body_specs;

import com.greethy.annotation.reactive.Handler;
import com.greethy.nutrition.api.rest.dto.request.CreateBodySpecsRequest;
import com.greethy.nutrition.core.port.in.command.CreateBodySpecsCommand;
import lombok.RequiredArgsConstructor;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Handler
@RequiredArgsConstructor
public class BodySpecsCommandsEndpointHandler {

    private final ModelMapper mapper;

    private final ReactorCommandGateway reactorCommandGateway;

    Mono<ServerResponse> createUserBodySpecs(ServerRequest serverRequest) {
        String userId = serverRequest.pathVariable("user_id");
        return serverRequest.bodyToMono(CreateBodySpecsRequest.class)
                .map(request -> mapper.map(request, CreateBodySpecsCommand.class))
                .doOnNext(command -> {
                    command.setBodySpecsId(UUID.randomUUID().toString());
                    command.setUserId(userId);
                })
                .flatMap(reactorCommandGateway::send)
                .flatMap(it -> ServerResponse.status(HttpStatus.CREATED)
                        .bodyValue(it));

    }

}
