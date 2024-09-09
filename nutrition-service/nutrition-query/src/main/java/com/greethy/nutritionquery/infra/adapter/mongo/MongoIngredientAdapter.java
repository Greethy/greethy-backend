package com.greethy.nutritionquery.infra.adapter.mongo;

import com.greethy.common.infra.component.annotation.DrivenAdapter;
import com.greethy.nutritioncommon.entity.Ingredient;
import com.greethy.nutritioncommon.repository.IngredientRepository;
import com.greethy.nutritionquery.domain.port.IngredientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    public Flux<Ingredient> findAllBy(Pageable pageable) {
        return repository.findAllBy(pageable);
    }

    @Override
    public Flux<Ingredient> findByGroup(String group, Pageable pageable) {
        return repository.findByGroup(group, pageable);
    }
}
