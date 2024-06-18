package com.greethy.nutritioncommand.domain.port;

import com.greethy.nutritioncommon.entity.Food;
import reactor.core.publisher.Mono;

public interface FoodPort {

    Mono<Food> save(Food food);

    Mono<Food> findById(String id);

    Mono<Food> findRandomByGroup(String group);

}
