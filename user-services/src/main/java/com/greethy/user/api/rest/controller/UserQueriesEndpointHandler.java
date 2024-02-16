package com.greethy.user.api.rest.controller;

import com.greethy.user.api.rest.dto.response.UserLookupResponse;
import com.greethy.user.core.port.in.query.FindAllUserQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserQueriesEndpointHandler {

    private final ReactorQueryGateway queryGateway;

    public Mono<ServerResponse> findAllUser() {
        return queryGateway.query(new FindAllUserQuery(), ResponseTypes.instanceOf(UserLookupResponse.class))
                .switchIfEmpty(Mono.just(UserLookupResponse.builder().build()))
                .doOnNext(System.out::println)
                .flatMap(response -> {
                            if (response.getUsers().isEmpty()) {
                                return ServerResponse.status(HttpStatus.NO_CONTENT).body("No user found", String.class);
                            }
                            return ServerResponse.ok()
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .bodyValue(response);
                        }
                );

    }

    public Mono<ServerResponse> findUserByUsername(ServerRequest serverRequest) {
        return null;
    }


}
