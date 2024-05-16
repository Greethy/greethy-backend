package com.greethy.nutrition.api.rest.controller.handler;

import java.util.Objects;

import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.greethy.annotation.reactive.EndpointHandler;
import com.greethy.core.api.response.PageSupport;
import com.greethy.core.util.ServerRequestUtil;
import com.greethy.nutrition.api.rest.dto.response.IngredientResponse;
import com.greethy.nutrition.core.port.in.query.CountAllIngredientQuery;
import com.greethy.nutrition.core.port.in.query.FindIngredientByIdQuery;
import com.greethy.nutrition.core.port.in.query.FindIngredientsWithPaginationQuery;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@EndpointHandler
@RequiredArgsConstructor
public class IngredientQueriesHandler {

    private final ReactorQueryGateway queryGateway;

    public Mono<ServerResponse> getIngredientById(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("ingredient-id"))
                .map(FindIngredientByIdQuery::new)
                .flatMap(query -> queryGateway.query(query, IngredientResponse.class))
                .flatMap(response -> Objects.nonNull(response)
                        ? ServerResponse.ok().bodyValue(response)
                        : ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> getIngredientsWithPagination(ServerRequest serverRequest) {
        int offset = ServerRequestUtil.getQueryParamIntValue(serverRequest, "offset", "0");
        int limit = ServerRequestUtil.getQueryParamIntValue(serverRequest, "limit", "10");
        return Flux.just(FindIngredientsWithPaginationQuery.builder()
                        .limit(limit)
                        .offset(offset)
                        .build())
                .flatMap(query -> queryGateway.streamingQuery(query, IngredientResponse.class))
                .collectList()
                .zipWith(queryGateway.query(new CountAllIngredientQuery(), Long.class))
                .map(zippedResponse -> new PageSupport<>(zippedResponse.getT1(), offset, limit, zippedResponse.getT2()))
                .flatMap(pageResponse -> pageResponse.content().isEmpty()
                        ? ServerResponse.noContent().build()
                        : ServerResponse.ok().bodyValue(pageResponse));
    }
}
