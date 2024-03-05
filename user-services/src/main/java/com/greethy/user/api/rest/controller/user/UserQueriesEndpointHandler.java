package com.greethy.user.api.rest.controller.user;

import com.greethy.user.api.rest.dto.response.UserLookupResponse;
import com.greethy.user.core.port.in.query.FindAllUserQuery;
import com.greethy.user.core.port.in.query.GetAllUserWithPageableQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserQueriesEndpointHandler {

    private final ModelMapper mapper;

    private final String DEFAULT_PAGE_VALUE = "0";

    private final String DEFAULT_SIZE_VALUE = "10";

    private final ReactorQueryGateway queryGateway;

    public Mono<ServerResponse> findAllUser() {
        return queryGateway.query(new FindAllUserQuery(), ResponseTypes.instanceOf(UserLookupResponse.class))
                .switchIfEmpty(Mono.just(UserLookupResponse.builder().build()))
                .doOnNext(System.out::println)
                .flatMap(this::handleQueryResponse);

    }

    public Mono<ServerResponse> findAllUserWithPageable(ServerRequest serverRequest) {
        Integer page = serverRequest.queryParam("page")
                .filter(pageValue -> !pageValue.isEmpty())
                .or(() -> Optional.of(DEFAULT_PAGE_VALUE))
                .map(Integer::valueOf).get();
        Integer size = serverRequest.queryParam("size")
                .filter(sizeValue -> !sizeValue.isEmpty())
                .or(() -> Optional.of(DEFAULT_SIZE_VALUE))
                .map(Integer::valueOf).get();
        var query = GetAllUserWithPageableQuery.builder()
                .page(page).size(size).sort("userId")
                .build();
        return queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponse.class))
                .switchIfEmpty(Mono.just(UserLookupResponse.builder().build()))
                .flatMap(this::handleQueryResponse);
    }

    private Mono<ServerResponse> handleQueryResponse(UserLookupResponse response) {
        if (response.getUsers().isEmpty()) {
            return ServerResponse.status(HttpStatus.NO_CONTENT)
                    .body(Mono.just("No user found"), String.class);
        }
        System.out.println("abc");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(response);
    }

    public Mono<ServerResponse> findUserByUsername(ServerRequest serverRequest) {
        return null;
    }

}
