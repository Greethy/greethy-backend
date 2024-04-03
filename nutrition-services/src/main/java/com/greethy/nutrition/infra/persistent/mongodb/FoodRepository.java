package com.greethy.nutrition.infra.persistent.mongodb;

import com.greethy.nutrition.core.domain.entity.menu.Food;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends ReactiveMongoRepository<Food, String> {

}
