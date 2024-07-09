package com.greethy.nutritionquery.domain.service.impl;

import com.greethy.common.api.response.ObjectIdResponse;
import com.greethy.nutritioncommon.dto.request.query.GetFoodRecommendationQuery;
import com.greethy.nutritioncommon.dto.response.FoodResponse;
import com.greethy.nutritionquery.domain.port.FoodPort;
import com.greethy.nutritionquery.domain.port.GorseClientPort;
import com.greethy.nutritionquery.domain.port.UserClientPort;
import com.greethy.nutritionquery.domain.service.RecommendationQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecommendationQueryServiceImpl implements RecommendationQueryService {

    private final FoodPort foodPort;
    private final ModelMapper mapper;
    private final UserClientPort userClientPort;
    private final GorseClientPort gorseClientPort;

    @Override
    public Flux<FoodResponse> getMeFoodRecommendation(GetFoodRecommendationQuery query) {
        return Mono.from(userClientPort.getUserIdByUsername(query.username()))
                .map(ObjectIdResponse::id)
                .flatMap(userId -> gorseClientPort.getFoodRecommendation(userId, "food", 10))
                .flatMapMany(Flux::fromArray)
                .flatMap(foodPort::findById)
                .map(food -> mapper.map(food, FoodResponse.class));
    }
}
