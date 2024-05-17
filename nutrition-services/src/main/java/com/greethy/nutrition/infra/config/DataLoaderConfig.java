package com.greethy.nutrition.infra.config;

import com.greethy.nutrition.core.domain.entity.Food;
import com.greethy.nutrition.core.domain.value.BmiEvaluate;
import com.greethy.nutrition.core.domain.value.BmrByAge;
import com.greethy.nutrition.core.domain.value.PalEvaluate;
import com.greethy.nutrition.core.domain.value.Range;
import com.greethy.nutrition.core.domain.value.enums.Meal;
import com.greethy.nutrition.core.port.out.BmiEvaluatePort;
import com.greethy.nutrition.core.port.out.BmrByAgePort;
import com.greethy.nutrition.core.port.out.FoodPort;
import com.greethy.nutrition.core.port.out.PalEvaluatePort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * The {@code DataLoaderConfig} class is responsible for initializing data upon application startup.
 * This class listens for the ApplicationReadyEvent and triggers the data initialization process.
 *
 * @author Kien N.Thanh
 */
@Slf4j
@Component
public class DataLoaderConfig {

    private final FoodPort foodPort;

    private final BmrByAgePort bmrByAgePort;

    private final PalEvaluatePort palEvaluatePort;

    private final BmiEvaluatePort bmiEvaluatePort;

    public DataLoaderConfig(@Qualifier("mongoFoodAdapter") FoodPort foodPort,
                            BmrByAgePort bmrByAgePort,
                            BmiEvaluatePort bmiEvaluatePort,
                            PalEvaluatePort palEvaluatePort) {
        this.foodPort = foodPort;
        this.bmrByAgePort = bmrByAgePort;
        this.bmiEvaluatePort = bmiEvaluatePort;
        this.palEvaluatePort = palEvaluatePort;
    }


    @EventListener(value = ApplicationReadyEvent.class)
    void init() {
        log.info("Start data initialization....");

        foodPort.deleteAll()
                .thenMany(Flux.fromIterable(foods()))
                .flatMap(foodPort::save)
                .subscribe();

        Mono.from(bmiEvaluatePort.deleteAll())
                .thenMany(Flux.just(bmiEvaluates()))
                .flatMap(bmiEvaluatePort::saveAll)
                .then(bmrByAgePort.deleteAll())
                .thenMany(Flux.just(bmrByAgesTable()))
                .flatMap(bmrByAgePort::saveAll)
                .then(palEvaluatePort.deleteAll())
                .thenMany(Flux.just(palEvaluates()))
                .flatMap(palEvaluatePort::saveAll)
                .subscribe();
    }

    private List<Food> foods() {
        var breakfastFoods = IntStream.iterate(0, i -> i + 1)
                .limit(1000).boxed()
                .map(Object::toString)
                .map(foodId -> {
                    var food = new Food();
                    food.setId(foodId);
                    food.setMeal(Meal.BREAKFAST.getName());
                    food.setTotalCalories(new Random().nextDouble(50, 400));
                    if (Integer.parseInt(foodId) >= 0 && Integer.parseInt(foodId) < 200) {
                        food.setGroup("cereal");
                        food.getRelatedIds().setSoupIds(generateRandomIds(200, 400));
                        food.getRelatedIds().setProteinIds(generateRandomIds(400, 600));
                        food.getRelatedIds().setVegetableIds(generateRandomIds(600, 800));
                        food.getRelatedIds().setDesertIds(generateRandomIds(800, 1000));
                    } else if (Integer.parseInt(foodId) >= 200 && Integer.parseInt(foodId) < 400) {
                        food.setGroup("soup");
                        food.getRelatedIds().setCerealIds(generateRandomIds(0, 200));
                        food.getRelatedIds().setProteinIds(generateRandomIds(400, 600));
                        food.getRelatedIds().setVegetableIds(generateRandomIds(600, 800));
                        food.getRelatedIds().setDesertIds(generateRandomIds(800, 1000));
                    } else if (Integer.parseInt(foodId) >= 400 && Integer.parseInt(foodId) < 600) {
                        food.setGroup("protein");
                        food.getRelatedIds().setCerealIds(generateRandomIds(0, 200));
                        food.getRelatedIds().setSoupIds(generateRandomIds(200, 400));
                        food.getRelatedIds().setVegetableIds(generateRandomIds(600, 800));
                        food.getRelatedIds().setDesertIds(generateRandomIds(800, 1000));
                    } else if (Integer.parseInt(foodId) >= 600 && Integer.parseInt(foodId) < 800) {
                        food.setGroup("vegetable");
                        food.getRelatedIds().setCerealIds(generateRandomIds(0, 200));
                        food.getRelatedIds().setProteinIds(generateRandomIds(400, 600));
                        food.getRelatedIds().setSoupIds(generateRandomIds(200, 400));
                        food.getRelatedIds().setDesertIds(generateRandomIds(800, 1000));
                    } else if (Integer.parseInt(foodId) >= 800 && Integer.parseInt(foodId) < 1000) {
                        food.setGroup("desert");
                        food.getRelatedIds().setCerealIds(generateRandomIds(0, 200));
                        food.getRelatedIds().setProteinIds(generateRandomIds(400, 600));
                        food.getRelatedIds().setSoupIds(generateRandomIds(200, 400));
                        food.getRelatedIds().setVegetableIds(generateRandomIds(600, 800));
                    }
                    return food;
                })
                .toList();

        var lunchFoods = IntStream.iterate(1000, i -> i + 1)
                .limit(1000)
                .boxed()
                .map(Object::toString)
                .map(foodId -> {
                    var food = new Food();
                    food.setId(foodId);
                    food.setMeal(Meal.LUNCH.getName());
                    food.setTotalCalories(new Random().nextDouble(300, 700));
                    if (Integer.parseInt(foodId) >= 1000 && Integer.parseInt(foodId) < 1200) {
                        food.setGroup("cereal");
                        food.getRelatedIds().setSoupIds(generateRandomIds(1200, 1400));
                        food.getRelatedIds().setProteinIds(generateRandomIds(1400, 1600));
                        food.getRelatedIds().setVegetableIds(generateRandomIds(1600, 1800));
                        food.getRelatedIds().setDesertIds(generateRandomIds(1800, 2000));
                    } else if (Integer.parseInt(foodId) >= 1200 && Integer.parseInt(foodId) < 1400) {
                        food.setGroup("soup");
                        food.getRelatedIds().setCerealIds(generateRandomIds(1000, 1200));
                        food.getRelatedIds().setProteinIds(generateRandomIds(1400, 1600));
                        food.getRelatedIds().setVegetableIds(generateRandomIds(1600, 1800));
                        food.getRelatedIds().setDesertIds(generateRandomIds(1800, 2000));
                    } else if (Integer.parseInt(foodId) >= 1400 && Integer.parseInt(foodId) < 1600) {
                        food.setGroup("protein");
                        food.getRelatedIds().setCerealIds(generateRandomIds(0, 200));
                        food.getRelatedIds().setSoupIds(generateRandomIds(200, 400));
                        food.getRelatedIds().setVegetableIds(generateRandomIds(600, 800));
                        food.getRelatedIds().setDesertIds(generateRandomIds(800, 1000));
                    } else if (Integer.parseInt(foodId) >= 1600 && Integer.parseInt(foodId) < 1800) {
                        food.setGroup("vegetable");
                        food.getRelatedIds().setCerealIds(generateRandomIds(1000, 1200));
                        food.getRelatedIds().setProteinIds(generateRandomIds(1400, 1600));
                        food.getRelatedIds().setSoupIds(generateRandomIds(1200, 1400));
                        food.getRelatedIds().setDesertIds(generateRandomIds(1800, 2000));
                    } else if (Integer.parseInt(foodId) >= 1800 && Integer.parseInt(foodId) < 2000) {
                        food.setGroup("desert");
                        food.getRelatedIds().setCerealIds(generateRandomIds(1000, 1200));
                        food.getRelatedIds().setProteinIds(generateRandomIds(1400, 1600));
                        food.getRelatedIds().setSoupIds(generateRandomIds(1200, 1400));
                        food.getRelatedIds().setVegetableIds(generateRandomIds(1600, 1800));
                    }
                    return food;
                }).toList();
        return Stream.concat(breakfastFoods.stream(), lunchFoods.stream())
                .collect(Collectors.toList());
    }

    private Set<String> generateRandomIds(int start, int end) {
        return ThreadLocalRandom.current()
                .ints(start, end)
                .limit(21)
                .skip(1)
                .boxed()
                .map(Object::toString)
                .collect(Collectors.toSet());
    }

    private Set<BmiEvaluate> bmiEvaluates() {
        Set<BmiEvaluate> bmiEvaluates = new HashSet<>();
        bmiEvaluates.add(new BmiEvaluate("Underweight III", new Range(0d, 16d)));
        bmiEvaluates.add(new BmiEvaluate("Underweight II", new Range(16d, 16.9d)));
        bmiEvaluates.add(new BmiEvaluate("Underweight I", new Range(17d, 18.4d)));
        bmiEvaluates.add(new BmiEvaluate("Normal", new Range(18.5d, 22.9d)));
        bmiEvaluates.add(new BmiEvaluate("Pre-obese", new Range(23d, 24.9d)));
        bmiEvaluates.add(new BmiEvaluate("Obesity I", new Range(25d, 29.9d)));
        bmiEvaluates.add(new BmiEvaluate("Obesity II", new Range(30d, Double.MAX_VALUE)));
        return bmiEvaluates;
    }

    private Set<BmrByAge> bmrByAgesTable() {
        Set<BmrByAge> bmrByAges = new HashSet<>();
        bmrByAges.add(new BmrByAge(new Range(0d, 2d), 61d));
        bmrByAges.add(new BmrByAge(new Range(3d, 5d), 54.8d));
        bmrByAges.add(new BmrByAge(new Range(6d, 7d), 44.3d));
        bmrByAges.add(new BmrByAge(new Range(8d, 9d), 40.8d));
        bmrByAges.add(new BmrByAge(new Range(10d, 11d), 37.4d));
        bmrByAges.add(new BmrByAge(new Range(12d, 14d), 31d));
        bmrByAges.add(new BmrByAge(new Range(15d, 19d), 27.8d));
        bmrByAges.add(new BmrByAge(new Range(20d, 29d), 24d));
        bmrByAges.add(new BmrByAge(new Range(30d, 39d), 22.3d));
        bmrByAges.add(new BmrByAge(new Range(50d, Double.MAX_VALUE), 21.5d));
        return bmrByAges;
    }

    private Set<PalEvaluate> palEvaluates() {
        Set<PalEvaluate> palEvaluates = new HashSet<>();
        palEvaluates.add(new PalEvaluate(new Range(0d, 0d), 0d, 0d, 0d));
        palEvaluates.add(new PalEvaluate(new Range(1d, 2d), 1.35d, 1.35d, 1.35d));
        palEvaluates.add(new PalEvaluate(new Range(3d, 5d), 1.45d, 1.45d, 1.45d));
        palEvaluates.add(new PalEvaluate(new Range(6d, 7d), 1.35d, 1.55d, 1.74d));
        palEvaluates.add(new PalEvaluate(new Range(8d, 9d), 1.40d, 1.6d, 1.8d));
        palEvaluates.add(new PalEvaluate(new Range(10d, 11d), 1.45d, 1.65d, 1.85d));
        palEvaluates.add(new PalEvaluate(new Range(12d, 14d), 1.5d, 1.7d, 1.9d));
        palEvaluates.add(new PalEvaluate(new Range(15d, 19d), 1.55d, 1.75d, 1.95d));
        palEvaluates.add(new PalEvaluate(new Range(20d, 29d), 1.5d, 1.75d, 2d));
        palEvaluates.add(new PalEvaluate(new Range(30d, 49d), 1.5d, 1.75d, 2d));
        palEvaluates.add(new PalEvaluate(new Range(50d, 69d), 1.5d, 1.75d, 2d));
        palEvaluates.add(new PalEvaluate(new Range(70d, Double.MAX_VALUE), 1.45d, 1.7d, 1.95d));
        return palEvaluates;
    }
}
