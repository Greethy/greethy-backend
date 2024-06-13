package com.greethy.nutritioncommon.entity;

import com.greethy.common.domain.entity.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;


@Data
@Builder
@Document(collection = "foods")
@EqualsAndHashCode(callSuper = true)
public class Food extends BaseEntity {

    @Id
    private String id;

    private String name;

    private String meal;

    private String group;

    private String recipe;

    private String tips;

    @Field(name = "image_url")
    private String imageUrl;

    @Field(name = "instruction_url")
    private String instructionUrl;

    private List<String> labels;

    private List<Ingredient> ingredients;

    @Field(name = "total_calories")
    private Integer totalCalories;

}
