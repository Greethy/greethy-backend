package com.greethy.nutrition.infra.repository.mongodb.adapter;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.domain.entity.Food;
import com.greethy.nutrition.core.port.out.read.FindFoodPort;
import com.greethy.nutrition.infra.repository.mongodb.FoodRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@InfrastructureAdapter
@RequiredArgsConstructor
public class MongoFindFoodAdapter implements FindFoodPort {

    private final FoodRepository repository;

    @Override
    public Mono<Food> findById(String id) {
        return repository.findById(id);
    }
}
