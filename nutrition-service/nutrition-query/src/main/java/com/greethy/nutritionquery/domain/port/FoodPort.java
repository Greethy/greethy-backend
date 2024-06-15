package com.greethy.nutritionquery.domain.port;

import com.greethy.nutritioncommon.entity.Food;
import reactor.core.publisher.Flux;

public interface FoodPort {

    Flux<Food> findAll();

}
