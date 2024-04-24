package com.greethy.nutrition.infra.repository.mongodb.adapter;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.domain.value.Ingredient;
import com.greethy.nutrition.core.port.out.food.SaveIngredientPort;
import com.greethy.nutrition.infra.repository.mongodb.IngredientRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@InfrastructureAdapter
@RequiredArgsConstructor
public class MongoSaveIngredientAdapter implements SaveIngredientPort {

    private final IngredientRepository repository;

    @Override
    public Flux<Ingredient> saveAll(Iterable<Ingredient> ingredients) {
        return repository.saveAll(ingredients);
    }
}
