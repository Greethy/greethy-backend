package com.greethy.nutritionquery.domain.port;

import com.greethy.nutritioncommon.dto.response.GorseSimilarityResponse;
import reactor.core.publisher.Flux;

public interface GorseClientPort {

    Flux<GorseSimilarityResponse> getSimilarityItems(String itemId, String category, Integer size);

}
