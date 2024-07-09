package com.greethy.nutritionquery.domain.port;

import com.greethy.nutritioncommon.dto.response.GorseSimilarityResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GorseClientPort {

    Flux<GorseSimilarityResponse> getSimilarityItems(String itemId, String category, Integer size);

    Mono<String[]> getFoodRecommendation(String userId, String category, Integer size);
}
