package com.greethy.nutrition.core.port.out.read;

import com.greethy.nutrition.core.domain.entity.Food;

import reactor.core.publisher.Mono;

public interface FindFoodPort {

    Mono<Food> findById(String id);
}
