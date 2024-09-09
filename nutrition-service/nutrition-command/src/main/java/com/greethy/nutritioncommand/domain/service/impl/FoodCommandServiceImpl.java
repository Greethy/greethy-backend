package com.greethy.nutritioncommand.domain.service.impl;

import com.greethy.common.api.response.ObjectIdResponse;
import com.greethy.common.infra.component.i18n.Translator;
import com.greethy.nutritioncommand.domain.port.FoodPort;
import com.greethy.nutritioncommand.domain.port.IngredientPort;
import com.greethy.nutritioncommand.domain.service.FoodCommandService;
import com.greethy.nutritioncommon.constant.Constants;
import com.greethy.nutritioncommon.dto.request.command.CreateFoodCommand;
import com.greethy.nutritioncommon.dto.request.command.UpdateFoodCommand;
import com.greethy.nutritioncommon.dto.response.FoodResponse;
import com.greethy.nutritioncommon.entity.Food;
import com.greethy.nutritioncommon.entity.FoodIngredient;
import com.greethy.nutritioncommon.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class FoodCommandServiceImpl implements FoodCommandService {

    private final FoodPort foodPort;
    private final ModelMapper mapper;
    private final Translator translator;
    private final IngredientPort ingredientPort;

    @Override
    public Mono<ObjectIdResponse> createFood(CreateFoodCommand command) {
        return Flux.fromIterable(command.getFoodIngredients())
                .flatMap(foodIngredientDto -> ingredientPort.findById(foodIngredientDto.getIngredientId())
                        .map(ingredient -> FoodIngredient.builder()
                                .ingredientId(ingredient.getId())
                                .name(ingredient.getName())
                                .value(foodIngredientDto.getValue())
                                .unit(foodIngredientDto.getUnit())
                                .prepare(foodIngredientDto.getPrepare())
                                .calories(ingredient.getCaloriesPer100g() * (foodIngredientDto.getValue() / 100))
                                .build()
                        )
                ).collectList()
                .map(foodIngredients -> {
                    var food = mapper.map(command, Food.class);
                    food.setFoodIngredients(foodIngredients);
                    food.setTotalCalories(foodIngredients.stream()
                            .mapToDouble(FoodIngredient::getCalories) // Lấy giá trị calories từ mỗi Ingredient
                            .sum());
                    return food;
                }).flatMap(foodPort::save)
                .doOnSuccess(food -> log.info("Food created: {}", food.getId()))
                .map(food -> new ObjectIdResponse(food.getId()));
    }

    @Override
    public Mono<FoodResponse> updateFood(String foodId, UpdateFoodCommand command) {
        return foodPort.findById(foodId)
                .switchIfEmpty(Mono.error(this::foodNotFoundException))
                .doOnNext(food -> {
                    food.setName(command.getName());
                    food.setGroup(command.getGroup().getName());
                    food.setMeal(command.getMeal().getName());
                    food.setRecipe(command.getRecipe());
                    food.setMoreInfo(command.getMoreInfo());
                    food.setTips(command.getTips());
                    food.setUpdatedAt(new Date());
                })
                .flatMap(food -> Flux.fromIterable(command.getFoodIngredients())
                        .flatMap(foodIngredientDto -> ingredientPort.findById(foodIngredientDto.getIngredientId())
                                .map(ingredient -> FoodIngredient.builder()
                                        .ingredientId(ingredient.getId())
                                        .name(ingredient.getName())
                                        .value(foodIngredientDto.getValue())
                                        .unit(foodIngredientDto.getUnit())
                                        .prepare(foodIngredientDto.getPrepare())
                                        .calories(ingredient.getCaloriesPer100g() * (foodIngredientDto.getValue() / 100))
                                        .build()
                                )
                        ).collectList()
                        .map(foodIngredients -> {
                            food.setFoodIngredients(foodIngredients);
                            food.setTotalCalories(foodIngredients.stream()
                                    .mapToDouble(FoodIngredient::getCalories) // Lấy giá trị calories từ mỗi Ingredient
                                    .sum());
                            return food;
                        })
                ).flatMap(foodPort::save)
                .map(food -> mapper.map(food, FoodResponse.class));
    }

    @Override
    public Mono<Void> deleteFood(String foodId) {
        return foodPort.deleteById(foodId)
                .doOnSuccess(unused -> log.info("Food deleted: {}", foodId));
    }

    private NotFoundException foodNotFoundException() {
        String message = translator.getLocalizedMessage(Constants.MessageKeys.FOOD_NOT_FOUND);
        return new NotFoundException(message);
    }


}
