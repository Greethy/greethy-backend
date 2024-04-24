package com.greethy.nutrition.infra.repository.mongodb.adapter;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.domain.entity.Food;
import com.greethy.nutrition.core.port.out.food.SaveFoodPort;
import com.greethy.nutrition.infra.repository.mongodb.FoodRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
@InfrastructureAdapter("mongodb-save-adapter")
public class MongoSaveFoodAdapter implements SaveFoodPort {

    private final FoodRepository repository;

    @Override
    public Mono<Food> save(Food food) {
        return repository.save(food);
    }

}
