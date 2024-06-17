package com.greethy.nutritionquery.api.rest.handler;

import com.greethy.common.api.handler.ExceptionHandler;
import com.greethy.common.infra.component.annotation.EndpointHandler;
import com.greethy.nutritioncommon.dto.request.query.GetMeFoodRecommendationQuery;
import com.greethy.nutritioncommon.dto.response.FoodResponse;
import com.greethy.nutritionquery.domain.service.RecommendationQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@EndpointHandler
@RequiredArgsConstructor
public class RecommendQueryHandler {

    private final ExceptionHandler exceptionHandler;

    private final RecommendationQueryService recommendationQueryService;

    public Mono<ServerResponse> getMeFoodRecommendations(ServerRequest serverRequest) {
        var fluxResponse = Flux.from(serverRequest.principal())
                .map(principal -> new GetMeFoodRecommendationQuery(principal.getName()))
                .flatMap(recommendationQueryService::getMeFoodRecommendation)
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fluxResponse, FoodResponse.class)
                .onErrorResume(exceptionHandler::handlingException);
    }

}
