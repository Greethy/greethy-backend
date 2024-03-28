package com.greethy.nutrition.api.rest.controller.specs;

import com.greethy.annotation.reactive.Handler;
import com.greethy.nutrition.api.rest.dto.response.BodySpecsResponse;
import com.greethy.nutrition.api.rest.dto.response.PageSupport;
import com.greethy.nutrition.core.port.in.query.CountAllBodySpecsQuery;
import com.greethy.nutrition.core.port.in.query.FindAllBodySpecsQuery;
import com.greethy.nutrition.core.port.in.query.FindBodySpecsByIdQuery;
import com.greethy.nutrition.core.port.in.query.FindBodySpecsByPaginationQuery;
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

    private final String DEFAULT_PAGE_VALUE = "0";

    private final String DEFAULT_SIZE_VALUE = "10";

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
        int page = serverRequest.queryParam("page")
                .filter(StringUtils::hasText)
                .or(() -> Optional.of(DEFAULT_PAGE_VALUE))
                .map(Integer::valueOf).get();
        int size = serverRequest.queryParam("size")
                .filter(StringUtils::hasText)
                .or(() -> Optional.of(DEFAULT_SIZE_VALUE))
                .map(Integer::valueOf).get();
        return Flux.just(FindBodySpecsByPaginationQuery.builder().page(page).size(size).build())
                .flatMap(query -> reactiveQueryGateway.streamingQuery(query, BodySpecsResponse.class))
                .collectList()
                .zipWith(reactiveQueryGateway.query(new CountAllBodySpecsQuery(), Long.class))
                .map(response -> new PageSupport<>(response.getT1(), page, size, response.getT2()))
                .flatMap(response -> ServerResponse.ok()
                        .bodyValue(response)
                ).switchIfEmpty(ServerResponse.noContent().build());
    }

    Mono<ServerResponse> getAllUserBodySpecs(ServerRequest serverRequest) {
        return null;
    }

}
