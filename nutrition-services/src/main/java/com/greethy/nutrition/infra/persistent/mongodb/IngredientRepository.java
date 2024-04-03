package com.greethy.nutrition.infra.persistent.mongodb;

import com.greethy.nutrition.core.domain.entity.menu.Ingredient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends ReactiveMongoRepository<Ingredient, String> {
}
