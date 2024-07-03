package com.greethy.nutritionquery.domain.port;

import com.greethy.nutritioncommon.entity.Ingredient;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface IngredientPort {

    Mono<Ingredient> findById(String id);

    Flux<Ingredient> findAllBy(Pageable pageable);

    Flux<Ingredient> findByGroup(String groupName, Pageable pageable);

}
