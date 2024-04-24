package com.greethy.nutrition.core.port.out.read;

import com.greethy.nutrition.core.domain.entity.Ingredient;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface FindIngredientPort {

    Mono<Long> countAll();

    Mono<Ingredient> findById(String id);

    Flux<Ingredient> findAll();

    Flux<Ingredient> findByPagination(Pageable pageable);

}
