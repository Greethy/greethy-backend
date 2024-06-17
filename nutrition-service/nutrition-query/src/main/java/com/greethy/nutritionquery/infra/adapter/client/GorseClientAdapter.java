package com.greethy.nutritionquery.infra.adapter.client;

import com.greethy.common.infra.component.annotation.DrivenAdapter;
import com.greethy.nutritionquery.domain.port.GorseClientPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@DrivenAdapter
@RequiredArgsConstructor
public class GorseClientAdapter implements GorseClientPort {

    public Flux<String> getUserFoodRecommendation(String userId) {
        return null;
    }

}
