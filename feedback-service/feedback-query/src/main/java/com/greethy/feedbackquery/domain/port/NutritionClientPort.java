package com.greethy.feedbackquery.domain.port;

import com.greethy.feedbackcommon.dto.response.ObjectIdResponse;
import reactor.core.publisher.Flux;

public interface NutritionClientPort {

    Flux<ObjectIdResponse> getAllFoodIds();

}
