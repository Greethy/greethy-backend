package com.greethy.nutrition.api.rest.controller.handler;

import java.util.Objects;

import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.greethy.annotation.reactive.EndpointHandler;
import com.greethy.core.api.handler.ExceptionHandler;
import com.greethy.core.api.response.PageSupport;
import com.greethy.core.domain.query.CheckIfUserExistsQuery;
import com.greethy.core.domain.query.FindUserBodySpecsIdsPaginationQuery;
import com.greethy.core.domain.query.FindUserBodySpecsIdsQuery;
import com.greethy.core.infra.util.ServerRequestUtil;
import com.greethy.nutrition.api.rest.dto.response.BodySpecsResponse;
import com.greethy.nutrition.core.domain.exception.NotFoundException;
import com.greethy.nutrition.core.port.in.query.*;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@EndpointHandler
@RequiredArgsConstructor
public class BodySpecsQueriesEndpointHandler {

    private final ReactorQueryGateway queryGateway;

    private final ExceptionHandler exceptionHandler;

    public Mono<ServerResponse> getBodySpecsById(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("body-specs-id"))
                .map(bodySpecsId -> FindBodySpecsByIdQuery.builder()
                        .bodySpecsId(bodySpecsId)
                        .build())
                .flatMap(query -> queryGateway.query(query, BodySpecsResponse.class))
                .flatMap(response -> Objects.isNull(response)
                        ? ServerResponse.noContent().build()
                        : ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> getBodySpecsPagination(ServerRequest serverRequest) {
        int offset = ServerRequestUtil.getQueryParamIntValue(serverRequest, "offset", "0");
        int limit = ServerRequestUtil.getQueryParamIntValue(serverRequest, "limit", "10");

        return Flux.just(FindBodySpecsWithPaginationQuery.builder()
                        .offset(offset)
                        .limit(limit)
                        .build())
                .flatMap(query -> queryGateway.streamingQuery(query, BodySpecsResponse.class))
                .collectList()
                .zipWith(queryGateway.query(new CountAllBodySpecsQuery(), Long.class))
                .map(zippedResponse -> new PageSupport<>(zippedResponse.getT1(), offset, limit, zippedResponse.getT2()))
                .flatMap(pageResponse -> pageResponse.content().isEmpty()
                        ? ServerResponse.noContent().build()
                        : ServerResponse.ok().bodyValue(pageResponse));
    }

    public Mono<ServerResponse> getAllUserBodySpecs(ServerRequest serverRequest) {
        String userId = serverRequest.pathVariable("user-id");
        return Flux.just(userId)
                .map(CheckIfUserExistsQuery::new)
                .flatMap(query -> queryGateway
                        .query(query, Boolean.class)
                        .filter(Boolean::booleanValue)
                        .switchIfEmpty(Mono.error(NotFoundException::new)))
                .thenMany(Flux.just(userId))
                .map(FindUserBodySpecsIdsQuery::new)
                .flatMap(query -> queryGateway.streamingQuery(query, String.class))
                .collectList()
                .map(bodySpecsIds -> FindAllBodySpecsByIdQuery.builder()
                        .bodySpecsIds(bodySpecsIds)
                        .build())
                .flatMapMany(query -> queryGateway.streamingQuery(query, BodySpecsResponse.class))
                .collectList()
                .flatMap(responses -> ServerResponse.ok().bodyValue(responses))
                .onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> getUserBodySpecsWithPagination(ServerRequest serverRequest) {
        int offset = ServerRequestUtil.getQueryParamIntValue(serverRequest, "offset", "0");
        int limit = ServerRequestUtil.getQueryParamIntValue(serverRequest, "limit", "10");
        String userId = serverRequest.pathVariable("user-id");

        return Flux.just(userId)
                .map(CheckIfUserExistsQuery::new)
                .flatMap(query -> queryGateway
                        .query(query, Boolean.class)
                        .filter(Boolean::booleanValue)
                        .switchIfEmpty(Mono.error(NotFoundException::new)))
                .thenMany(Flux.just(FindUserBodySpecsIdsPaginationQuery.builder()
                        .userId(userId)
                        .offset(offset)
                        .limit(limit)
                        .build()))
                .flatMap(query -> queryGateway.streamingQuery(query, String.class))
                .collectList()
                .map(bodySpecsIds -> FindAllBodySpecsByIdQuery.builder()
                        .bodySpecsIds(bodySpecsIds)
                        .build())
                .flatMapMany(query -> queryGateway.streamingQuery(query, BodySpecsResponse.class))
                .collectList()
                .flatMap(responses -> ServerResponse.ok().bodyValue(responses))
                .onErrorResume(exceptionHandler::handlingException);
    }
}
