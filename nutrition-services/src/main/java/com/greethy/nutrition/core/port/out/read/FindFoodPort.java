package com.greethy.nutrition.core.port.out.read;

import org.springframework.data.domain.Pageable;

import com.greethy.nutrition.core.domain.entity.Food;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FindFoodPort {

    Mono<Long> countAll();

    Mono<Food> findById(String id);

    Flux<Food> findAllBy(Pageable pageable);
}
