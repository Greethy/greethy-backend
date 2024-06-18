package com.greethy.nutritioncommon.repository;

import com.greethy.nutritioncommon.entity.Food;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface FoodRepository extends ReactiveMongoRepository<Food, String> {

    Flux<Food> findByNameRegex(String name, Pageable pageable);

    Flux<Food> findAllBy(Pageable pageable);

    @Aggregation(pipeline = {
            "{ $match: { 'group': ?0 } }",
            "{ $sample: { size: 1 } }"
    })
    Mono<Food> findRandomByGroup(String group);

}
