package com.greethy.nutrition.infra.repository.mongodb.adapter;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.domain.value.Ingredient;
import com.greethy.nutrition.core.port.out.food.FindIngredientPort;
import com.greethy.nutrition.infra.repository.mongodb.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@InfrastructureAdapter
@RequiredArgsConstructor
public class MongoFindIngredientAdapter implements FindIngredientPort {

    private final IngredientRepository repository;

    @Override
    public Mono<Long> countAll() {
        return repository.count();
    }

    @Override
    public Mono<Ingredient> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Ingredient> findAll() {
        return repository.findAll();
    }

    @Override
    public Flux<Ingredient> findByPagination(Pageable pageable) {
        return repository.findAllBy(pageable);
    }

}
