package com.greethy.user.api.rest.handler;

import com.greethy.annotation.reactive.EndpointHandler;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@EndpointHandler
public class UserCommandHandler {

    public Mono<ServerResponse> registerUser(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> updateUser(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> deleteUserTemporary(ServerRequest serverRequest) {
        return null;
    }

}
