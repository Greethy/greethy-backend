package com.greethy.nutritionquery.api.rest.handler;

import com.greethy.common.api.handler.ExceptionHandler;
import com.greethy.common.infra.component.annotation.EndpointHandler;
import com.greethy.common.infra.util.ServerRequestUtil;
import com.greethy.nutritioncommon.dto.request.query.GetByIdQuery;
import com.greethy.nutritioncommon.dto.request.query.GetByPaginationQuery;
import com.greethy.nutritioncommon.dto.request.query.SearchByNameQuery;
import com.greethy.nutritioncommon.dto.response.IngredientResponse;
import com.greethy.nutritionquery.domain.service.IngredientQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@EndpointHandler
@RequiredArgsConstructor
public class IngredientQueryHandler {

    private final ExceptionHandler exceptionHandler;

    private final IngredientQueryService ingredientService;

    public Mono<ServerResponse> getIngredientPagination(ServerRequest serverRequest) {
        Integer offset = ServerRequestUtil.getQueryParamIntegerValue(serverRequest, "offset", "0");
        Integer limit = ServerRequestUtil.getQueryParamIntegerValue(serverRequest, "limit", "10");
        String sort = ServerRequestUtil.getQueryParamStringValue(serverRequest, "sort", "+id");
        var fluxResponse = Flux.just(GetByPaginationQuery.builder()
                        .offset(offset).limit(limit)
                        .sort(sort).build())
                .flatMap(ingredientService::getIngredientsByPagination);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fluxResponse, IngredientResponse.class)
                .onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> getIngredientById(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("ingredient-id"))
                .map(GetByIdQuery::new)
                .flatMap(ingredientService::getIngredientById)
                .flatMap(response -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> getIngredientsByGroup(ServerRequest serverRequest) {
        Integer offset = ServerRequestUtil.getQueryParamIntegerValue(serverRequest, "offset", "0");
        Integer limit = ServerRequestUtil.getQueryParamIntegerValue(serverRequest, "limit", "10");
        String sort = ServerRequestUtil.getQueryParamStringValue(serverRequest, "sort", "+id");
        String groupName = serverRequest.pathVariable("group-name");
        var fluxResponse = Flux.just(SearchByNameQuery.builder()
                        .offset(offset).limit(limit)
                        .name(groupName).sort(sort)
                        .build())
                .flatMap(ingredientService::getIngredientsByGroup);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fluxResponse, IngredientResponse.class)
                .onErrorResume(exceptionHandler::handlingException);
    }

}
