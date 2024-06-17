package com.greethy.nutritionquery.api.rest.handler;

import com.greethy.common.api.handler.ExceptionHandler;
import com.greethy.common.infra.component.annotation.EndpointHandler;
import com.greethy.common.infra.util.ServerRequestUtil;
import com.greethy.nutritioncommon.dto.request.query.GetByIdQuery;
import com.greethy.nutritioncommon.dto.request.query.GetByPaginationQuery;
import com.greethy.nutritioncommon.dto.request.query.SearchByNameQuery;
import com.greethy.nutritioncommon.dto.response.FoodResponse;
import com.greethy.nutritionquery.domain.service.FoodQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@EndpointHandler
@RequiredArgsConstructor
public class FoodQueryHandler {

    private final ExceptionHandler exceptionHandler;

    private final FoodQueryService foodQueryService;

    public Mono<ServerResponse> getFoodById(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("food-id"))
                .map(GetByIdQuery::new)
                .flatMap(foodQueryService::getFoodById)
                .flatMap(response -> ServerResponse
                        .ok().contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(response))
                .onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> searchByFoodName(ServerRequest serverRequest) {
        String foodName = ServerRequestUtil.getQueryParamStringValue(serverRequest, "name", "");
        Integer offset = ServerRequestUtil.getQueryParamIntegerValue(serverRequest, "offset", "0");
        Integer limit = ServerRequestUtil.getQueryParamIntegerValue(serverRequest, "limit", "10");
        String sort = ServerRequestUtil.getQueryParamStringValue(serverRequest, "sort", "+id");
        var fluxResponse = Flux.just(SearchByNameQuery.builder()
                        .name(foodName).offset(offset)
                        .limit(limit).sort(sort).build())
                .flatMap(foodQueryService::searchFoodByName);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fluxResponse, FoodResponse.class)
                .onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> getFoodsPagination(ServerRequest serverRequest) {
        Integer offset = ServerRequestUtil.getQueryParamIntegerValue(serverRequest, "offset", "0");
        Integer limit = ServerRequestUtil.getQueryParamIntegerValue(serverRequest, "limit", "10");
        String sort = ServerRequestUtil.getQueryParamStringValue(serverRequest, "sort", "+id");
        var fluxResponse = Flux.just(new GetByPaginationQuery(offset, limit, sort))
                .flatMap(foodQueryService::getFoodsByPagination);
        return ServerResponse
                .ok().contentType(MediaType.APPLICATION_JSON)
                .body(fluxResponse, FoodResponse.class)
                .onErrorResume(exceptionHandler::handlingException);
    }

    public Mono<ServerResponse> getAllFoodIds() {
        return foodQueryService.getAllFoodIds()
                .collectList()
                .flatMap(idsResponse -> ServerResponse.ok().bodyValue(idsResponse))
                .onErrorResume(exceptionHandler::handlingException);
    }

}
