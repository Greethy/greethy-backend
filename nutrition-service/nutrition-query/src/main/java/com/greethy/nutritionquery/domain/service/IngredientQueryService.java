package com.greethy.nutritionquery.domain.service;

import com.greethy.nutritioncommon.dto.request.query.GetByIdQuery;
import com.greethy.nutritioncommon.dto.response.IngredientResponse;
import reactor.core.publisher.Mono;

public interface IngredientQueryService {

    Mono<IngredientResponse> getIngredientById(GetByIdQuery query);
}
