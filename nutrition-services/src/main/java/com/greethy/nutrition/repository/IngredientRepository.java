package com.greethy.nutrition.repository;

import com.greethy.nutrition.entity.Ingredient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends ReactiveMongoRepository<Ingredient, String> {
}
