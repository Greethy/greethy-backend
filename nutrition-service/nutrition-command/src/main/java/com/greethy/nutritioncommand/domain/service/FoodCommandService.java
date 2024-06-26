package com.greethy.nutritioncommand.domain.service;

import com.greethy.common.api.response.ObjectIdResponse;
import com.greethy.nutritioncommon.dto.request.command.CreateFoodCommand;
import com.greethy.nutritioncommon.dto.request.command.UpdateFoodCommand;
import com.greethy.nutritioncommon.dto.response.FoodResponse;
import reactor.core.publisher.Mono;

public interface FoodCommandService {

    Mono<ObjectIdResponse> createFood(CreateFoodCommand command);

    Mono<FoodResponse> updateFood(String foodId, UpdateFoodCommand command);

    Mono<Void> deleteFood(String foodId);

}
