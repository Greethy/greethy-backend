package com.greethy.nutrition.infra.repository.mongodb;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.greethy.nutrition.core.domain.entity.Ingredient;

import reactor.core.publisher.Flux;

@Repository
public interface IngredientRepository extends ReactiveMongoRepository<Ingredient, String> {

    Flux<Ingredient> findAllBy(Pageable pageable);
}
