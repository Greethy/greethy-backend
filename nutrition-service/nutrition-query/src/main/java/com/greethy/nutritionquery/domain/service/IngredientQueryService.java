package com.greethy.nutritionquery.domain.service;

import com.greethy.nutritioncommon.dto.request.query.GetByIdQuery;
import com.greethy.nutritioncommon.dto.request.query.GetByPaginationQuery;
import com.greethy.nutritioncommon.dto.request.query.SearchByNameQuery;
import com.greethy.nutritioncommon.dto.response.IngredientResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IngredientQueryService {

    Mono<IngredientResponse> getIngredientById(GetByIdQuery query);

    Flux<IngredientResponse> getIngredientsByPagination(GetByPaginationQuery query);

    Flux<IngredientResponse> getIngredientsByGroup(SearchByNameQuery query);
}
