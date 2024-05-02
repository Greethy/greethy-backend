package com.greethy.nutrition.core.domain.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.greethy.nutrition.core.domain.value.Nutrient;
import com.greethy.nutrition.core.domain.value.Owner;
import com.greethy.nutrition.core.event.FoodCreatedEvent;
import com.greethy.nutrition.core.event.IngredientsAddedToFoodEvent;
import com.greethy.nutrition.core.port.in.command.AddIngredientsToFoodCommand;
import com.greethy.nutrition.core.port.in.command.CreateFoodCommand;
import com.greethy.nutrition.core.port.out.read.FindIngredientPort;
import com.greethy.nutrition.core.port.out.read.GetUserPort;

import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

@Data
@Aggregate
@NoArgsConstructor
@Document(collection = "foods")
public class Food {

    @Id
    @AggregateIdentifier
    private String id;

    private String name;

    @Field(name = "food_types")
    private List<String> foodTypes;

    @Field("have_for")
    private String haveFor;

    private String description;

    @Field(name = "image_url")
    private String imageUrl;

    @Field(name = "video_url")
    private String videoUrl;

    @Field(name = "total_calories")
    private Double totalCalories = 0.0d;

    private String recipe;

    private String tips;

    private String locale;

    private Boolean open;

    private Owner owner;

    @Field(name = "created_at")
    private LocalDateTime createdAt;

    @Field(name = "updated_at")
    private LocalDateTime updatedAt;

    private List<FoodIngredient> ingredients = new ArrayList<>();

    private List<Nutrient> nutrients;

    @CommandHandler
    Food(CreateFoodCommand command, ModelMapper mapper, GetUserPort userPort) {
        var foodCreatedEvent = userPort.getById(command.getUserId())
                .map(user -> {
                    var owner = mapper.map(user, Owner.class);
                    var event = mapper.map(command, FoodCreatedEvent.class);
                    event.setOwner(owner);
                    event.setCreatedAt(LocalDateTime.now());
                    return event;
                })
                .block();
        AggregateLifecycle.apply(foodCreatedEvent);
    }

    @EventSourcingHandler
    void on(FoodCreatedEvent event) {
        this.id = event.getFoodId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.imageUrl = event.getImageUrl();
        this.videoUrl = event.getVideoUrl();
        this.recipe = event.getRecipe();
        this.tips = event.getTips();
        this.open = event.getOpen();
        this.createdAt = event.getCreatedAt();
        this.owner = event.getOwner();
    }

    @CommandHandler
    void handle(AddIngredientsToFoodCommand command, FindIngredientPort ingredientPort) {
        var foodIngredients = command.getFoodIngredients().stream()
                .peek(foodIngredient -> Mono.just(foodIngredient.getIngredientId())
                        .flatMap(ingredientPort::findById)
                        .doOnNext(ingredient -> {
                            Double rate = foodIngredient.getValue() / 100;
                            foodIngredient.setName(ingredient.getName());
                            foodIngredient.setCalories(ingredient.getCalories() * rate);
                        })
                        .block())
                .collect(Collectors.toList());
        var totalCalories = foodIngredients.stream()
                .parallel()
                .map(FoodIngredient::getCalories)
                .reduce(0.0d, Double::sum);
        AggregateLifecycle.apply(IngredientsAddedToFoodEvent.builder()
                .totalCalories(totalCalories)
                .foodId(command.getFoodId())
                .foodIngredients(foodIngredients)
                .build());
    }

    @EventSourcingHandler
    void on(IngredientsAddedToFoodEvent event) {
        this.ingredients.addAll(event.getFoodIngredients());
        this.totalCalories += event.getTotalCalories();
        this.updatedAt = event.getUpdatedAt();
    }
}
