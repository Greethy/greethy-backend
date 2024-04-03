package com.greethy.nutrition.api.rest.controller.food;

import com.greethy.annotation.reactive.EndpointHandler;
import com.greethy.nutrition.api.rest.dto.response.IngredientResponse;
import com.greethy.nutrition.core.port.in.query.FindAllIngredientQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@EndpointHandler
@RequiredArgsConstructor
public class FoodQueriesEndpointHandler {

    private final ReactorQueryGateway queryGateway;

    public Mono<ServerResponse> getAllIngredients() {
        return Flux.just(new FindAllIngredientQuery())
                .flatMap(query -> queryGateway.streamingQuery(query, IngredientResponse.class))
                .collectList()
                .flatMap(ingredientsResponse -> ingredientsResponse.isEmpty()
                        ? ServerResponse.noContent().build()
                        : ServerResponse.ok().bodyValue(ingredientsResponse)
                );
    }

}
