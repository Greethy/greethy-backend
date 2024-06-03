package com.greethy.nutritioncommand.api.rest.handler;

import com.greethy.annotation.reactive.EndpointHandler;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@EndpointHandler
public class BodySpecCommandHandler {

    public Mono<ServerResponse> createUserBodySpec(ServerRequest serverRequest) {
        return null;
    }

}
