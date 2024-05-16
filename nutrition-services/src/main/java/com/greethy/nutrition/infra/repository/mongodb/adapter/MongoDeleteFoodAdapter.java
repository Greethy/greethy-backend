package com.greethy.nutrition.infra.repository.mongodb.adapter;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.port.out.write.DeleteFoodPort;
import com.greethy.nutrition.infra.repository.mongodb.FoodRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@InfrastructureAdapter
@RequiredArgsConstructor
public class MongoDeleteFoodAdapter implements DeleteFoodPort {

    private final FoodRepository repository;


    @Override
    public Mono<Void> deleteAll() {
        return repository.deleteAll();
    }
}
