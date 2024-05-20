package com.greethy.nutrition.core.port.out;

import org.springframework.data.domain.Pageable;

import com.greethy.nutrition.core.domain.entity.Food;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FoodPort {

    Mono<Food> save(Food food);

    Mono<Boolean> existsById(String id);

    Mono<Void> deleteAll();

    Mono<Long> countAll();

    Mono<Food> findById(String id);

    Flux<Food> saveAll(Iterable<Food> foods);

    Flux<Food> findAll();

    Flux<Food> findAllBy(Pageable pageable);

}
