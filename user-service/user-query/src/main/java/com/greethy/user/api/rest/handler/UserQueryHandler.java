package com.greethy.user.api.rest.handler;

import com.greethy.annotation.reactive.EndpointHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@EndpointHandler
@RequiredArgsConstructor
public class UserQueryHandler {

    public Mono<ServerResponse> getUserById(ServerRequest serverRequest) {

        return null;
    }

}
