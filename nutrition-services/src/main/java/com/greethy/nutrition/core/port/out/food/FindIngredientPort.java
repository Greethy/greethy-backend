package com.greethy.nutrition.core.port.out.food;

import com.greethy.nutrition.core.domain.entity.menu.Ingredient;
import reactor.core.publisher.Flux;

public interface FindIngredientPort {

    Flux<Ingredient> findAll();

}
