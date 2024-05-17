package com.greethy.nutrition.core.domain.service;

import com.greethy.nutrition.core.domain.entity.Food;
import com.greethy.nutrition.core.event.FoodCreatedEvent;
import com.greethy.nutrition.core.event.IngredientsAddedToFoodEvent;
import com.greethy.nutrition.core.port.out.FoodPort;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class FoodEventHandler {

    private final FoodPort port;

    private final ModelMapper mapper;

    public FoodEventHandler(@Qualifier("mongoFoodAdapter") FoodPort port, ModelMapper mapper) {
        this.port = port;
        this.mapper = mapper;
    }


    @EventHandler
    void on(FoodCreatedEvent event) {
        Mono.just(event)
                .map(foodCreatedEvent -> mapper.map(foodCreatedEvent, Food.class))
                .flatMap(port::save)
                .subscribe(food -> log.info("created food {}", food.getId()));
    }

    @EventHandler
    void on(IngredientsAddedToFoodEvent event) {
        port.findById(event.getFoodId())
                .doOnNext(food -> {
                    var totalCalories = food.getTotalCalories() + event.getTotalCalories();
                    food.setTotalCalories(totalCalories);
                    food.getIngredients().addAll(event.getFoodIngredients());
                })
                .flatMap(port::save)
                .subscribe();
    }
}
