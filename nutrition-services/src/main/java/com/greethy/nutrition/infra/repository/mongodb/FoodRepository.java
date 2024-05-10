package com.greethy.nutrition.infra.repository.mongodb;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.greethy.nutrition.core.domain.entity.Food;

import reactor.core.publisher.Flux;

@Repository
public interface FoodRepository extends ReactiveMongoRepository<Food, String> {

    Flux<Food> findAllBy(Pageable pageable);
}
