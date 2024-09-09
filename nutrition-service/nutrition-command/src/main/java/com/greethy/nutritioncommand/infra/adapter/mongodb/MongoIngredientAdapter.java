package com.greethy.nutritioncommand.infra.adapter.mongodb;

import com.greethy.common.infra.component.annotation.DrivenAdapter;
import com.greethy.nutritioncommand.domain.port.IngredientPort;
import com.greethy.nutritioncommon.entity.Ingredient;
import com.greethy.nutritioncommon.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@DrivenAdapter
@RequiredArgsConstructor
public class MongoIngredientAdapter implements IngredientPort {

    private final IngredientRepository repository;


    @Override
    public Mono<Ingredient> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Ingredient> findAllByIds(Iterable<String> ids) {
        return repository.findAllById(ids);
    }
}
