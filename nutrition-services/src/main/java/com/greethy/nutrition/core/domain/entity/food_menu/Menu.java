package com.greethy.nutrition.core.domain.entity.food_menu;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "menus")
public class Menu {

    private String id;

    private String menuType;

    @Field(name = "total_calories")
    private Integer totalCalories;

    private List<Meal> meals;

}
