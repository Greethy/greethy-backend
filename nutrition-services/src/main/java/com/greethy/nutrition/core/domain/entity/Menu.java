package com.greethy.nutrition.core.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "menus")
public class Menu {

    @Id
    private String id;

    private List<String> labels;

    private List<String> types;

    private List<Food> breakfast;

    private List<Food> lunch;

    private List<Food> dinner;

    @Field(name = "total_calories")
    private Integer totalCalories;

    @Field(name = "created_at")
    private LocalDateTime createdAt;
}
