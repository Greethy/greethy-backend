package com.greethy.nutritioncommand.domain.service;

import com.greethy.common.api.response.ObjectIdResponse;
import com.greethy.nutritioncommon.dto.request.command.CreateFoodCommand;
import reactor.core.publisher.Mono;

public interface FoodCommandService {

    Mono<ObjectIdResponse> createFood(CreateFoodCommand request);

}
