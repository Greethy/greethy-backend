package com.greethy.nutritioncommand;

import com.greethy.common.infra.util.RandomUtil;
import com.greethy.nutritioncommand.domain.port.GorseClientPort;
import com.greethy.nutritioncommon.entity.*;
import com.greethy.nutritioncommon.entity.enums.FoodLabel;
import com.greethy.nutritioncommon.entity.enums.Group;
import com.greethy.nutritioncommon.entity.enums.Meal;
import com.greethy.nutritioncommon.entity.value.Range;
import com.greethy.nutritioncommon.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication(
        scanBasePackages = {
                "com.greethy.common",
                "com.greethy.nutritioncommon",
                "com.greethy.nutritioncommand"
        })
public class NutritionCommandApplication implements CommandLineRunner {

    private final GorseClientPort gorsePort;

    private final FoodRepository foodRepository;

    private final BmrByAgeRepository bmrByAgeRepository;

    private final IngredientRepository ingredientRepository;

    private final BmiEvaluateRepository bmiEvaluateRepository;

    private final PalEvaluateRepository palEvaluateRepository;

    public static void main(String[] args) {
        SpringApplication.run(NutritionCommandApplication.class);
    }

    @Override
    public void run(String... args) {
        foodRepository.count()
                .filter(count -> count > 0)
                .switchIfEmpty(Mono.when(
                        foodRepository.deleteAll()
                                .doOnSuccess(unused -> log.info("Deleted Foods collections")),
                        bmiEvaluateRepository.deleteAll()
                                .doOnSuccess(unused -> log.info("Deleted BMI-Evaluates collections")),
                        bmrByAgeRepository.deleteAll()
                                .doOnSuccess(unused -> log.info("Deleted BMR-by-Ages collections")),
                        palEvaluateRepository.deleteAll()
                                .doOnSuccess(unused -> log.info("Deleted Pal-Evaluates collections")),
                        ingredientRepository.deleteAll()
                                .doOnSuccess(unused -> log.info("Deleted Ingredient Collections"))
                ).then(Mono.when(
                                Flux.fromIterable(bmiEvaluates())
                                        .flatMap(bmiEvaluateRepository::save)
                                        .doOnNext(bmiEvaluate -> log.info("BMI Evaluate: {} saved to MongoDB.", bmiEvaluate)),
                                Flux.fromIterable(bmrByAgesTable())
                                        .flatMap(bmrByAgeRepository::save)
                                        .doOnNext(bmrByAge -> log.info("BMR by Age: {} saved to MongoDB.", bmrByAge)),
                                Flux.fromIterable(palEvaluates())
                                        .flatMap(palEvaluateRepository::save)
                                        .doOnNext(palEvaluate -> log.info("PAL Evaluate: {} saved to MongoDB.", palEvaluate)),
                                Flux.fromIterable(ingredients())
                                        .flatMap(ingredientRepository::save)
                                        .doOnNext(ingredient -> log.info("Ingredient: {} saved to MongoDB", ingredient)),
                                Flux.fromIterable(foods())
                                        .flatMap(foodRepository::save)
                                        .delaySequence(Duration.ofSeconds(3))
                                        .flatMap(food -> {
                                            var item = GorseItem.builder()
                                                    .itemId(food.getId())
                                                    .labels(food.getLabels())
                                                    .isHidden(false)
                                                    .timestamp(LocalDateTime.now())
                                                    .categories(List.of("food", food.getGroup(), food.getMeal()))
                                                    .build();
                                            return gorsePort.saveItem(item).thenReturn(food);
                                        }).doOnNext(food -> log.info("Food: {} saved to MongoDB", food))
                        )
                ).thenReturn(0L))
                .subscribe(count -> log.info("Data is loaded to DB"));
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
        bmrByAges.add(new BmrByAge(new Range(30d, 49d), 22.3d));
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

    private List<Food> foods() {
        var faker = new Faker(new Locale("en"));
        var foodNames = IntStream.iterate(0, i -> i + 1)
                .limit(10000)
                .mapToObj(i -> {
                    var name = RandomUtil.getSingleRandomFromStrings(
                            faker.food().allergen(), faker.food().dish(), faker.food().spice(),
                            faker.food().fruit(), faker.food().sushi(), faker.food().vegetable(),
                            faker.food().ingredient(), faker.nigeria().food(), faker.slackEmoji().foodAndDrink()
                    );
                    return name + i;
                }).collect(Collectors.toSet());
        return foodNames.stream()
                .map(name -> {
                    var meals = Arrays.stream(Meal.values()).map(Meal::getName).toList();
                    var allLabels = Arrays.stream(FoodLabel.values()).map(FoodLabel::getLabel).toList();
                    var groups = Arrays.stream(Group.values()).map(Group::getName).toList();
                    var meal = RandomUtil.getSingleRandomFromStrings(meals);
                    var labels = RandomUtil.getListRandomFromStrings(RandomUtil.getSingleRandomInteger(5, 15), allLabels);
                    var group = RandomUtil.getSingleRandomFromStrings(groups);

                    labels.add(meal);
                    return Food.builder()
                            .id(name.trim().replaceAll("\\s+", "-"))
                            .name(name)
                            .labels(labels).meal(meal).group(group)
                            .recipe("This is recipe")
                            .imageUrls(List.of(faker.internet().image()))
                            .video(faker.internet().url())
                            //.foodIngredients(foodIngredients(RandomUtil.getSingleRandomInteger(5, 10)))
                            .totalCalories(RandomUtil.getSingleRandomInteger(100, 400))
                            .build();
                }).collect(Collectors.toList());
    }

    private List<FoodIngredient> foodIngredients(int size) {
        return IntStream.range(0, size)
                .mapToObj(i -> ingredientRepository.findById("" + i)
                        .map(ingredient -> FoodIngredient.builder().ingredientId(ingredient.getId())
                                .value(RandomUtil.getSingleRandomInteger(50, 200))
                                .name(ingredient.getName())
                                .calories((double) RandomUtil.getSingleRandomInteger(50, 300))
                                .unit("g")
                                .prepare("This is Preparation for " + ingredient.getName())
                                .build())
                        .block()
                ).collect(Collectors.toList());
    }

    private List<Ingredient> ingredients() {
        var faker = new Faker(new Locale("en"));
        var ingredientMap = IntStream.iterate(0, i -> i + 1)
                .limit(1000)
                .boxed()
                .collect(Collectors.toMap(
                        i -> i,
                        i -> RandomUtil.getSingleRandomFromStrings(faker.food().ingredient()) + i));
        return ingredientMap.entrySet()
                .stream()
                .map(entry -> Ingredient.builder()
                        .id(entry.getKey() + "")
                        .name(entry.getValue())
                        .calories(RandomUtil.getSingleRandomInteger(100, 300))
                        .build())
                .collect(Collectors.toList());
    }

}