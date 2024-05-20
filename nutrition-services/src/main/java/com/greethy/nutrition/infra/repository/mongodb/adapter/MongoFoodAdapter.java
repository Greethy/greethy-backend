package com.greethy.nutrition.infra.repository.mongodb.adapter;

import org.springframework.data.domain.Pageable;

import com.greethy.annotation.hexagonal.DrivenAdapter;
import com.greethy.nutrition.core.domain.entity.Food;
import com.greethy.nutrition.core.port.out.FoodPort;
import com.greethy.nutrition.infra.repository.mongodb.FoodRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@DrivenAdapter("mongoFoodAdapter")
public class MongoFoodAdapter implements FoodPort {

    private final FoodRepository repository;

    @Override
    public Mono<Food> save(Food food) {
        return repository.save(food);
    }

    @Override
    public Mono<Boolean> existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public Mono<Void> deleteAll() {
        return repository.deleteAll();
    }

    @Override
    public Mono<Long> countAll() {
        return repository.count();
    }

    @Override
    public Mono<Food> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Food> saveAll(Iterable<Food> foods) {
        return repository.saveAll(foods);
    }

    @Override
    public Flux<Food> findAll() {
        return repository.findAll();
    }

    @Override
    public Flux<Food> findAllBy(Pageable pageable) {
        return repository.findAllBy(pageable);
    }
}
