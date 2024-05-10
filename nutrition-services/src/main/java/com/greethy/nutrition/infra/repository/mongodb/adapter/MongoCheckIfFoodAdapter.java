package com.greethy.nutrition.infra.repository.mongodb.adapter;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.port.out.read.CheckIfFoodPort;
import com.greethy.nutrition.infra.repository.mongodb.FoodRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@InfrastructureAdapter
@RequiredArgsConstructor
public class MongoCheckIfFoodAdapter implements CheckIfFoodPort {

    private final FoodRepository repository;

    @Override
    public Mono<Boolean> existsById(String id) {
        return repository.existsById(id);
    }
}
