package com.greethy.nutrition.core.domain.service;

import com.greethy.nutrition.core.domain.entity.Food;
import com.greethy.nutrition.core.event.FoodCreatedEvent;
import com.greethy.nutrition.core.event.IngredientsAddedToFoodEvent;
import com.greethy.nutrition.core.port.out.read.FindFoodPort;
import com.greethy.nutrition.core.port.out.write.SaveFoodPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class FoodEventHandler {

    private final ModelMapper mapper;

    @EventHandler
    void on(FoodCreatedEvent event, @Qualifier("mongodb-save-adapter") SaveFoodPort savePort) {
        Mono.just(event)
                .map(foodCreatedEvent -> mapper.map(foodCreatedEvent, Food.class))
                .flatMap(savePort::save)
                .subscribe(food -> log.info("created food {}", food.getId()));
    }

    @EventHandler
    void on(IngredientsAddedToFoodEvent event, FindFoodPort findPort,
            @Qualifier("mongodb-save-adapter") SaveFoodPort savePort) {
        findPort.findById(event.getFoodId())
                .doOnNext(food -> {
                    var totalCalories = food.getTotalCalories() + event.getTotalCalories();
                    food.setTotalCalories(totalCalories);
                    food.getIngredients().addAll(event.getFoodIngredients());
                    food.setUpdatedAt(event.getUpdatedAt());
                })
                .flatMap(savePort::save)
                .subscribe();
    }

}
