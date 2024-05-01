package com.greethy.nutrition.infra.repository.mongodb;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.greethy.nutrition.core.domain.entity.Food;

@Repository
public interface FoodRepository extends ReactiveMongoRepository<Food, String> {}
