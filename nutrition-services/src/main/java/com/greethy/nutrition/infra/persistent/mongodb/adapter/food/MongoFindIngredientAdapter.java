package com.greethy.nutrition.infra.persistent.mongodb.adapter.food;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.domain.entity.menu.Ingredient;
import com.greethy.nutrition.core.port.out.food.FindIngredientPort;
import com.greethy.nutrition.infra.persistent.mongodb.IngredientRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@InfrastructureAdapter
@RequiredArgsConstructor
public class MongoFindIngredientAdapter implements FindIngredientPort {

    private final IngredientRepository repository;

    @Override
    public Flux<Ingredient> findAll() {
        return repository.findAll();
    }
}
