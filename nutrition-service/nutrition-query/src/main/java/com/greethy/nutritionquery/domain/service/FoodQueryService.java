package com.greethy.nutritionquery.domain.service;

import com.greethy.common.api.response.ObjectIdResponse;
import com.greethy.nutritioncommon.dto.request.query.GetByIdQuery;
import com.greethy.nutritioncommon.dto.request.query.GetByPaginationQuery;
import com.greethy.nutritioncommon.dto.request.query.SearchByNameQuery;
import com.greethy.nutritioncommon.dto.response.FoodResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FoodQueryService {

    Mono<FoodResponse> getFoodById(GetByIdQuery query);

    Flux<FoodResponse> searchFoodByName(SearchByNameQuery query);

    Flux<FoodResponse> getFoodsByPagination(GetByPaginationQuery query);

    Flux<ObjectIdResponse> getAllFoodIds();

}
