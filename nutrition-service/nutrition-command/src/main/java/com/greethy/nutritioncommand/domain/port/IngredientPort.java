package com.greethy.nutritioncommand.domain.port;

import com.greethy.nutritioncommon.entity.Ingredient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IngredientPort {

    Mono<Ingredient> findById(String id);

    Flux<Ingredient> findAllByIds(Iterable<String> ids);

}
