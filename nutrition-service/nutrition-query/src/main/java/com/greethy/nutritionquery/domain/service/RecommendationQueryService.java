package com.greethy.nutritionquery.domain.service;

import com.greethy.nutritioncommon.dto.request.query.GetMeFoodRecommendationQuery;
import com.greethy.nutritioncommon.dto.response.FoodResponse;
import reactor.core.publisher.Flux;

public interface RecommendationQueryService {

    Flux<FoodResponse> getMeFoodRecommendation(GetMeFoodRecommendationQuery query);

}
