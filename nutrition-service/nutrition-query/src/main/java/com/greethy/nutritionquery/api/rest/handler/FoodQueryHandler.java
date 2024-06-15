package com.greethy.nutritionquery.api.rest.handler;

import com.greethy.common.api.handler.ExceptionHandler;
import com.greethy.common.infra.component.annotation.EndpointHandler;
import com.greethy.nutritionquery.domain.service.FoodQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@EndpointHandler
@RequiredArgsConstructor
public class FoodQueryHandler {

    private final ExceptionHandler exceptionHandler;

    private final FoodQueryService foodQueryService;

    public Mono<ServerResponse> getAllFoodIds() {
        return foodQueryService.getAllFoodIds()
                .collectList()
                .flatMap(idsResponse -> ServerResponse.ok().bodyValue(idsResponse))
                .onErrorResume(exceptionHandler::handlingException);
    }

}
