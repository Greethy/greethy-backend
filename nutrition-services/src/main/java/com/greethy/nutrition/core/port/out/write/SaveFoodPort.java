package com.greethy.nutrition.core.port.out.write;

import com.greethy.nutrition.core.domain.entity.Food;

import reactor.core.publisher.Mono;

public interface SaveFoodPort {

    Mono<Food> save(Food food);
}
