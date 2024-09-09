package com.greethy.nutritioncommon.repository;

import com.greethy.nutritioncommon.entity.Ingredient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface IngredientRepository extends ReactiveMongoRepository<Ingredient, String> {

    Flux<Ingredient> findAllBy(Pageable pageable);

    Flux<Ingredient> findByGroup(String group, Pageable pageable);

}
