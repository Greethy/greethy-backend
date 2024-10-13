package com.greethy.account.api.rest.handler;

import com.greethy.account.api.rest.command.UserLoginCommand;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class AuthEndpointHandler {

    public Mono<ServerResponse> login(ServerRequest request) {

        return request.bodyToMono(UserLoginCommand.class)
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> logout(ServerRequest request) {
        return null;
    }

    public Mono<ServerResponse> signup(ServerRequest request) {
        return null;
    }

}
