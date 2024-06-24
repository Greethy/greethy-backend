package com.greethy.nutritionquery.domain.port;

import com.greethy.nutritioncommon.entity.Ingredient;
import reactor.core.publisher.Mono;

public interface IngredientPort {

    Mono<Ingredient> findById(String id);

}
