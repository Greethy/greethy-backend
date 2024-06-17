package com.greethy.nutritionquery.domain.service.impl;

import com.greethy.nutritioncommon.dto.request.query.GetMeFoodRecommendationQuery;
import com.greethy.nutritioncommon.dto.response.FoodResponse;
import com.greethy.nutritionquery.domain.port.GorseClientPort;
import com.greethy.nutritionquery.domain.port.UserClientPort;
import com.greethy.nutritionquery.domain.service.RecommendationQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecommendationQueryServiceImpl implements RecommendationQueryService {

    private final UserClientPort userClientPort;

    private final GorseClientPort gorseClientPort;

    @Override
    public Flux<FoodResponse> getMeFoodRecommendation(GetMeFoodRecommendationQuery query) {
        return null;
    }
}
