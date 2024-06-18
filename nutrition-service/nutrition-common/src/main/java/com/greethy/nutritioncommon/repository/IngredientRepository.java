package com.greethy.nutritioncommon.repository;

import com.greethy.nutritioncommon.entity.Ingredient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends ReactiveMongoRepository<Ingredient, String> {
}
