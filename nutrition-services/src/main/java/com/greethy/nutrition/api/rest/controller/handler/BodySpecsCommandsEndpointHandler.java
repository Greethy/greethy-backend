package com.greethy.nutrition.api.rest.controller.handler;

import com.greethy.annotation.reactive.EndpointHandler;
import com.greethy.nutrition.api.rest.dto.request.CreateBodySpecsRequest;
import com.greethy.nutrition.api.rest.dto.request.UpdateBodySpecsRequest;
import com.greethy.nutrition.core.port.in.command.CreateBodySpecsCommand;
import com.greethy.nutrition.core.port.in.command.DeleteBodySpecsCommand;
import com.greethy.nutrition.core.port.in.command.UpdateBodySpecsCommand;
import lombok.RequiredArgsConstructor;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.UUID;

@EndpointHandler
@RequiredArgsConstructor
public class BodySpecsCommandsEndpointHandler {

    private final ModelMapper mapper;

    private final ReactorCommandGateway commandGateway;

    /**
     * Creates Body Specifications for a user.
     * <p>
     * This method extracts the user ID from the path variable of the provided {@code serverRequest}.
     * It then reads the request body to obtain the body specifications to create.
     * Next, it maps the request body to a {@link CreateBodySpecsCommand} object using a mapper.
     * After mapping, it sets a unique ID for the body specifications and associates them with the user ID.
     * Finally, it sends the command to create the body specifications and returns an HTTP 201 Created response
     * with the ID of the created body specifications.
     *
     * @param serverRequest The server request containing the path variable "user-id" and the body specifications to create.
     * @return A {@link Mono} representing the server response. If the body specifications are created successfully,
     * it contains an HTTP 201 Created response with the ID of the created body specifications.
     */
    public Mono<ServerResponse> createUserBodySpecs(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("user-id"))
                .flatMap(userId -> serverRequest.bodyToMono(CreateBodySpecsRequest.class)
                        .map(request -> mapper.map(request, CreateBodySpecsCommand.class))
                        .doOnNext(command -> {
                            command.setBodySpecsId(UUID.randomUUID().toString());
                            command.setUserId(userId);
                        }))
                .flatMap(commandGateway::send)
                .flatMap(it -> ServerResponse.status(HttpStatus.CREATED)
                        .bodyValue(Map.of("body-spec-id", it))
                );
    }

    public Mono<ServerResponse> updateBodySpecs(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("body-specs-id"))
                .flatMap(bodySpecsId -> serverRequest.bodyToMono(UpdateBodySpecsRequest.class)
                        .map(request -> mapper.map(request, UpdateBodySpecsCommand.class))
                        .doOnNext(command -> command.setBodySpecsId(bodySpecsId))
                ).flatMap(commandGateway::send)
                .flatMap(it -> ServerResponse.status(HttpStatus.CREATED)
                        .bodyValue(Map.of("body-spec-id", it))
                );
    }

    public Mono<ServerResponse> deleteBodySpecs(ServerRequest serverRequest) {
        return Mono.just(DeleteBodySpecsCommand.builder()
                        .bodySpecsId(serverRequest.pathVariable("body-specs-id"))
                        .userId(serverRequest.pathVariable("user-id"))
                        .build())
                .flatMap(commandGateway::send)
                .flatMap(it -> ServerResponse.noContent().build());
    }

}
