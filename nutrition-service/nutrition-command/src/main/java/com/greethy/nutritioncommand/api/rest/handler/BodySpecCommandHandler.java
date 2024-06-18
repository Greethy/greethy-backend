package com.greethy.nutritioncommand.api.rest.handler;

import com.greethy.common.api.handler.ExceptionHandler;
import com.greethy.common.infra.component.annotation.EndpointHandler;
import com.greethy.nutritioncommand.domain.service.BodySpecCommandService;
import com.greethy.nutritioncommon.dto.request.command.CreateBodySpecCommand;
import com.greethy.nutritioncommon.dto.request.command.UpdateBodySpecCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.security.Principal;

@EndpointHandler
@RequiredArgsConstructor
public class BodySpecCommandHandler {

    private final ExceptionHandler exceptionHandler;

    private final BodySpecCommandService bodySpecService;

    public Mono<ServerResponse> createUserBodySpec(ServerRequest serverRequest) {

        return serverRequest.principal()
                .map(Principal::getName)
                .zipWith(serverRequest.bodyToMono(CreateBodySpecCommand.class))
                .flatMap(tuple2 -> bodySpecService.createBodySpec(tuple2.getT1(), tuple2.getT2()))
                .flatMap(response -> ServerResponse
                        .created(URI.create("/api/v1/body-specs/" + response.id()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(response)
                ).onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> updateBodySpec(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("body-spec-id"))
                .zipWith(serverRequest.bodyToMono(UpdateBodySpecCommand.class))
                .flatMap(tuple2 -> bodySpecService.updateBodySpec(tuple2.getT1(), tuple2.getT2()))
                .flatMap(response -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(response)
                ).onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> deleteBodySpec(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("body-spec-id"))
                .flatMap(bodySpecService::deleteBodySpec)
                .then(Mono.defer(() -> ServerResponse.noContent().build()));
    }

}
