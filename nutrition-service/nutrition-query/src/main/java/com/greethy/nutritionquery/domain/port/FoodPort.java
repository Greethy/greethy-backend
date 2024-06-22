package com.greethy.nutritionquery.domain.port;

import com.greethy.nutritioncommon.entity.Food;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FoodPort {

    Mono<Food> findById(String id);

    Flux<Food> findByNameRegex(String name, Pageable pageable);

    Flux<Food> findByPagination(Pageable pageable);

    Flux<Food> findAll();

    Mono<Food> findRandomByGroup(String group);

}
