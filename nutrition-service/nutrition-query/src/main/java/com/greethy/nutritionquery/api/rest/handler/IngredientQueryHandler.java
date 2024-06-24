package com.greethy.nutritionquery.api.rest.handler;

import com.greethy.common.api.handler.ExceptionHandler;
import com.greethy.common.infra.component.annotation.EndpointHandler;
import com.greethy.nutritioncommon.dto.request.query.GetByIdQuery;
import com.greethy.nutritionquery.domain.service.IngredientQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@EndpointHandler
@RequiredArgsConstructor
public class IngredientQueryHandler {

    private final ExceptionHandler exceptionHandler;

    private final IngredientQueryService ingredientService;

    public Mono<ServerResponse> getIngredientPagination(ServerRequest serverRequest) {
        return null;
    }

    public Mono<ServerResponse> getIngredientById(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("ingredient-id"))
                .map(GetByIdQuery::new)
                .flatMap(ingredientService::getIngredientById)
                .flatMap(response -> ServerResponse.ok().bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);
    }

}
