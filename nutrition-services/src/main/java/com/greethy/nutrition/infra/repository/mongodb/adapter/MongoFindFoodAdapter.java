package com.greethy.nutrition.infra.repository.mongodb.adapter;

import org.springframework.data.domain.Pageable;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.domain.entity.Food;
import com.greethy.nutrition.core.port.out.read.FindFoodPort;
import com.greethy.nutrition.infra.repository.mongodb.FoodRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@InfrastructureAdapter
@RequiredArgsConstructor
public class MongoFindFoodAdapter implements FindFoodPort {

    private final FoodRepository repository;

    @Override
    public Mono<Long> countAll() {
        return repository.count();
    }

    @Override
    public Mono<Food> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Food> findAllBy(Pageable pageable) {
        return repository.findAllBy(pageable);
    }
}
