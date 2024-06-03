package com.greethy.nutritioncommand.api.rest.handler;

import com.greethy.annotation.reactive.EndpointHandler;
import com.greethy.core.api.handler.ExceptionHandler;
import com.greethy.nutritioncommand.domain.service.BodySpecCommandService;
import com.greethy.nutritioncommon.dto.request.CreateBodySpecCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@EndpointHandler
@RequiredArgsConstructor
public class BodySpecCommandHandler {

    private final ExceptionHandler exceptionHandler;

    private final BodySpecCommandService bodySpecService;

    public Mono<ServerResponse> createUserBodySpec(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CreateBodySpecCommand.class)
                .flatMap(bodySpecService::createBodySpec)
                .flatMap(response -> ServerResponse.status(HttpStatus.CREATED)
                        .bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);
    }

}
