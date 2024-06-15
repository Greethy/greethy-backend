package com.greethy.nutritionquery.infra.adapter.mongo;

import com.greethy.common.infra.component.annotation.DrivenAdapter;
import com.greethy.nutritioncommon.entity.Food;
import com.greethy.nutritioncommon.repository.FoodRepository;
import com.greethy.nutritionquery.domain.port.FoodPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@DrivenAdapter
@RequiredArgsConstructor
public class MongoFoodAdapter implements FoodPort {

    private final FoodRepository repository;


    @Override
    public Flux<Food> findAll() {
        return repository.findAll();
    }
}
