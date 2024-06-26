package com.greethy.nutritioncommand.infra.adapter.mongodb;

import com.greethy.common.infra.component.annotation.DrivenAdapter;
import com.greethy.nutritioncommand.domain.port.FoodPort;
import com.greethy.nutritioncommon.entity.Food;
import com.greethy.nutritioncommon.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@DrivenAdapter
@RequiredArgsConstructor
public class MongoFoodAdapter implements FoodPort {

    private final FoodRepository repository;

    @Override
    public Mono<Food> save(Food food) {
        return repository.save(food);
    }

    @Override
    public Mono<Food> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Food> findRandomByGroup(String group) {
        return repository.findRandomByGroup(group);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }

}
