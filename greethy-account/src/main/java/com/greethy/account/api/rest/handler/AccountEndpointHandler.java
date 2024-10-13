package com.greethy.account.api.rest.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class AccountEndpointHandler {

    public Mono<ServerResponse> getCurrentAccount(ServerRequest request) {
        return null;
    }

}
