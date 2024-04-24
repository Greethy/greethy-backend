package com.greethy.nutrition.infra.microservice.gorse;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.domain.entity.Food;
import com.greethy.nutrition.core.port.out.food.SaveFoodPort;
import reactor.core.publisher.Mono;

@InfrastructureAdapter("gorse-save-adapter")
public class GorseSaveFoodItemAdapter implements SaveFoodPort {
    @Override
    public Mono<Food> save(Food food) {
        return null;
    }
}
