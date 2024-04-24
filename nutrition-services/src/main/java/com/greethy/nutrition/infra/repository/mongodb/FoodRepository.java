package com.greethy.nutrition.infra.repository.mongodb;

import com.greethy.nutrition.core.domain.entity.Food;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends ReactiveMongoRepository<Food, String> {

}
