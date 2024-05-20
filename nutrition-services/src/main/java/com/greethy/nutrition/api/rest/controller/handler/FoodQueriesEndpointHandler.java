package com.greethy.nutrition.api.rest.controller.handler;

import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.greethy.annotation.reactive.EndpointHandler;
import com.greethy.core.api.handler.ExceptionHandler;
import com.greethy.core.api.response.PageSupport;
import com.greethy.core.util.ServerRequestUtil;
import com.greethy.nutrition.api.rest.dto.response.FoodResponse;
import com.greethy.nutrition.core.domain.exception.NotFoundException;
import com.greethy.nutrition.core.port.in.query.*;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@EndpointHandler
@RequiredArgsConstructor
public class FoodQueriesEndpointHandler {

    private final ReactorQueryGateway queryGateway;

    private final ExceptionHandler exceptionHandler;

    public Mono<ServerResponse> getFoodById(ServerRequest serverRequest) {
        String foodId = serverRequest.pathVariable("food-id");
        return Mono.just(new CheckIfFoodExistsQuery(foodId))
                .flatMap(query -> queryGateway
                        .query(query, Boolean.class)
                        .filter(Boolean::booleanValue)
                        .switchIfEmpty(Mono.error(NotFoundException::new)))
                .then(Mono.just(new FindFoodByIdQuery(foodId)))
                .flatMap(query -> queryGateway.query(query, FoodResponse.class))
                .flatMap(response -> ServerResponse.ok().bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> getAllFood() {
        return Flux.just(new FindAllFoodQuery())
                .flatMap(query -> queryGateway.streamingQuery(query, FoodResponse.class))
                .collectList()
                .flatMap(foodResponses -> foodResponses.isEmpty()
                        ? ServerResponse.noContent().build()
                        : ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(foodResponses));
    }

    public Mono<ServerResponse> getFoodWithPagination(ServerRequest serverRequest) {
        int offset = ServerRequestUtil.getQueryParamIntValue(serverRequest, "offset", "0");
        int limit = ServerRequestUtil.getQueryParamIntValue(serverRequest, "limit", "20");

        return Flux.just(FindFoodWithPaginationQuery.builder()
                        .offset(offset)
                        .limit(limit)
                        .build())
                .flatMap(query -> queryGateway.streamingQuery(query, FoodResponse.class))
                .collectList()
                .zipWith(queryGateway.query(new CountAllFoodQuery(), Long.class))
                .map(zippedResponse -> new PageSupport<>(zippedResponse.getT1(), offset, limit, zippedResponse.getT2()))
                .flatMap(pageResponse -> pageResponse.content().isEmpty()
                        ? ServerResponse.noContent().build()
                        : ServerResponse.ok().bodyValue(pageResponse));
    }
}
