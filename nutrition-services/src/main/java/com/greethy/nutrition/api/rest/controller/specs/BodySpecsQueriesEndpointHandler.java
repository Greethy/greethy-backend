package com.greethy.nutrition.api.rest.controller.specs;

import com.greethy.annotation.reactive.Handler;
import com.greethy.core.domain.query.FindUserBodySpecsIdsQuery;
import com.greethy.nutrition.api.rest.dto.response.BodySpecsResponse;
import com.greethy.nutrition.api.rest.dto.response.PageSupport;
import com.greethy.nutrition.core.port.in.query.*;
import lombok.RequiredArgsConstructor;
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Handler
@RequiredArgsConstructor
public class BodySpecsQueriesEndpointHandler {

    private final ReactorQueryGateway reactiveQueryGateway;

    Mono<ServerResponse> getAllBodySpecs() {
        return Flux.just(new FindAllBodySpecsQuery())
                .flatMap(query -> reactiveQueryGateway.streamingQuery(query, BodySpecsResponse.class))
                .collectList()
                .flatMap(bodySpecsDtos -> ServerResponse.ok().bodyValue(bodySpecsDtos));
    }

    Mono<ServerResponse> getBodySpecsById(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("body-specs-id"))
                .map(bodySpecsId -> FindBodySpecsByIdQuery.builder().bodySpecsId(bodySpecsId).build())
                .flatMap(query -> reactiveQueryGateway.query(query, BodySpecsResponse.class))
                .flatMap(response -> ServerResponse.ok()
                        .bodyValue(response)
                ).switchIfEmpty(ServerResponse.noContent().build());
    }

    Mono<ServerResponse> getBodySpecsPagination(ServerRequest serverRequest) {
        int page = getQueryParamValue(serverRequest, "page", "0");
        int size = getQueryParamValue(serverRequest, "size", "10");
        return Flux.just(FindBodySpecsByPaginationQuery.builder().page(page).size(size).build())
                .flatMap(query -> reactiveQueryGateway.streamingQuery(query, BodySpecsResponse.class))
                .collectList()
                .zipWith(reactiveQueryGateway.query(new CountAllBodySpecsQuery(), Long.class))
                .map(response -> new PageSupport<>(response.getT1(), page, size, response.getT2()))
                .flatMap(response -> ServerResponse.ok()
                        .bodyValue(response)
                ).switchIfEmpty(ServerResponse.noContent().build());
    }

    private int getQueryParamValue(ServerRequest serverRequest, String name, String defaultValue) {
        return serverRequest.queryParam(name)
                .filter(StringUtils::hasText)
                .or(() -> Optional.of(defaultValue))
                .map(Integer::valueOf).get();
    }

    Mono<ServerResponse> getAllUserBodySpecs(ServerRequest serverRequest) {
        return Flux.just(serverRequest.pathVariable("user-id"))
                .map(userId -> FindUserBodySpecsIdsQuery.builder().userId(userId).build())
                .flatMap(query -> reactiveQueryGateway.streamingQuery(query, String.class))
                .collectList()
                .map(bodySpecsIds -> FindAllBodySpecsByIdQuery.builder().bodySpecsIds(bodySpecsIds).build())
                .flatMapMany(query -> reactiveQueryGateway.streamingQuery(query, BodySpecsResponse.class))
                .collectList()
                .flatMap(s -> ServerResponse.ok().bodyValue(s));
    }

}
