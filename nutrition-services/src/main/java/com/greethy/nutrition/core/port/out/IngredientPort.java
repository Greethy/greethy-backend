package com.greethy.nutrition.core.port.out;

import org.springframework.data.domain.Pageable;

import com.greethy.nutrition.core.domain.entity.Ingredient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IngredientPort {

    Mono<Long> countAll();

    Mono<Ingredient> findById(String id);

    Flux<Ingredient> findAll();

    Flux<Ingredient> findByPagination(Pageable pageable);

    Flux<Ingredient> saveAll(Iterable<Ingredient> ingredients);

}
