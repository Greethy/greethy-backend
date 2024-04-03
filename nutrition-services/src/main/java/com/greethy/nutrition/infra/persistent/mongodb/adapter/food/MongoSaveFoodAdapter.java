package com.greethy.nutrition.infra.persistent.mongodb.adapter.food;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.domain.entity.menu.Food;
import com.greethy.nutrition.core.port.out.food.SaveFoodPort;
import com.greethy.nutrition.infra.persistent.mongodb.FoodRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@InfrastructureAdapter
@RequiredArgsConstructor
public class MongoSaveFoodAdapter implements SaveFoodPort {

    private final FoodRepository foodRepository;

    @Override
    public Flux<Food> saveAll(Iterable<Food> foods) {
        return foodRepository.saveAll(foods);
    }
}
