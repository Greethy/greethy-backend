package com.greethy.nutrition.api.rest.controller.specs;

import com.greethy.annotation.reactive.EndpointHandler;
import com.greethy.core.api.response.PageSupport;
import com.greethy.core.domain.query.FindUserBodySpecsIdsQuery;
import com.greethy.core.util.ServerRequestUtil;
import com.greethy.nutrition.api.rest.dto.response.BodySpecsResponse;
import com.greethy.nutrition.core.port.in.query.*;
import lombok.RequiredArgsConstructor;
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@EndpointHandler
@RequiredArgsConstructor
public class BodySpecsQueriesEndpointHandler {

    private final ReactorQueryGateway queryGateway;

    public Mono<ServerResponse> getAllBodySpecs() {
        return Flux.just(new FindAllBodySpecsQuery())
                .flatMap(query -> queryGateway.streamingQuery(query, BodySpecsResponse.class))
                .collectList()
                .flatMap(bodySpecsResponse -> bodySpecsResponse.isEmpty()
                        ? ServerResponse.noContent().build()
                        : ServerResponse.ok().bodyValue(bodySpecsResponse)
                );
    }

    public Mono<ServerResponse> getBodySpecsById(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("body-specs-id"))
                .map(bodySpecsId -> FindBodySpecsByIdQuery.builder()
                        .bodySpecsId(bodySpecsId)
                        .build())
                .flatMap(query -> queryGateway.query(query, BodySpecsResponse.class))
                .flatMap(response -> Objects.isNull(response)
                        ? ServerResponse.noContent().build()
                        : ServerResponse.ok().bodyValue(response)
                );
    }

    public Mono<ServerResponse> getBodySpecsPagination(ServerRequest serverRequest) {
        int offset = ServerRequestUtil.getQueryParamIntValue(serverRequest, "offset", "0");
        int limit = ServerRequestUtil.getQueryParamIntValue(serverRequest, "limit", "10");
        return Flux.just(FindBodySpecsByPaginationQuery.builder()
                        .offset(offset).limit(limit)
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
        return Flux.just(serverRequest.pathVariable("user-id"))
                .map(userId -> FindUserBodySpecsIdsQuery.builder().userId(userId).build())
                .flatMap(query -> queryGateway.streamingQuery(query, String.class))
                .collectList()
                .map(bodySpecsIds -> FindAllBodySpecsByIdQuery.builder().bodySpecsIds(bodySpecsIds).build())
                .flatMapMany(query -> queryGateway.streamingQuery(query, BodySpecsResponse.class))
                .collectList()
                .flatMap(responses -> ServerResponse.ok().bodyValue(responses));
    }

}
