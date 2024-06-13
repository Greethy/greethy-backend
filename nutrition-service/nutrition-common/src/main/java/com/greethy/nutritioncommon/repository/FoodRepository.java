package com.greethy.nutritioncommon.repository;

import com.greethy.nutritioncommon.entity.Food;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends ReactiveMongoRepository<Food, String> {
}
