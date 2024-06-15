package com.greethy.nutritionquery.domain.service;

import com.greethy.nutritioncommon.dto.response.FoodIdResponse;
import reactor.core.publisher.Flux;

public interface FoodQueryService {
    Flux<FoodIdResponse> getAllFoodIds();
}
