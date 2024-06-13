package com.greethy.nutritionquery.api.rest.handler;

import com.greethy.common.infra.component.annotation.EndpointHandler;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@EndpointHandler
public class BodySpecQueryHandler {

    public Mono<ServerResponse> getBodySpecById(ServerRequest request) {
        return null;
    }

}
