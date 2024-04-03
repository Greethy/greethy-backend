package com.greethy.nutrition.core.port.out.food;

import com.greethy.nutrition.core.domain.entity.menu.Food;
import reactor.core.publisher.Flux;

public interface SaveFoodPort {

    Flux<Food> saveAll(Iterable<Food> foods);

}
