package com.greethy.nutrition.core.port.out.write;

import com.greethy.nutrition.core.domain.entity.Ingredient;
import reactor.core.publisher.Flux;

public interface SaveIngredientPort {

    Flux<Ingredient> saveAll(Iterable<Ingredient> ingredients);

}
