package com.greethy.nutritionquery.infra.adapter.mongo;

import com.greethy.common.infra.component.annotation.DrivenAdapter;
import com.greethy.nutritioncommon.entity.Food;
import com.greethy.nutritioncommon.repository.FoodRepository;
import com.greethy.nutritionquery.domain.port.FoodPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@DrivenAdapter
@RequiredArgsConstructor
public class MongoFoodAdapter implements FoodPort {

    private final FoodRepository repository;


    @Override
    public Mono<Food> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Food> findByNameRegex(String name, Pageable pageable) {
        return repository.findByNameRegex(name, pageable);
    }

    @Override
    public Flux<Food> findByPagination(Pageable pageable) {
        return repository.findAllBy(pageable);
    }

    @Override
    public Flux<Food> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Food> findRandomByGroup(String group) {
        return repository.findRandomByGroup(group);
    }
}
