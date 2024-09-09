package com.greethy.nutritionquery.api.rest.handler;

import com.greethy.common.api.handler.ExceptionHandler;
import com.greethy.common.infra.component.annotation.EndpointHandler;
import com.greethy.nutritioncommon.dto.request.query.GetByIdQuery;
import com.greethy.nutritionquery.domain.service.MenuQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.security.Principal;

@EndpointHandler
@RequiredArgsConstructor
public class MenuQueryHandler {

    private final MenuQueryService menuQueryService;

    private final ExceptionHandler exceptionHandler;

    public Mono<ServerResponse> getMenuById(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("menu-id"))
                .map(GetByIdQuery::new)
                .flatMap(menuQueryService::getMenuById)
                .flatMap(response -> ServerResponse.ok().bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> suggestMenuFoods(ServerRequest serverRequest) {
        return serverRequest.principal()
                .map(Principal::getName)
                .flatMap(menuQueryService::suggestMenuFoods)
                .flatMap(response -> ServerResponse.ok().bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);
    }

}
