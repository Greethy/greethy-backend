package com.greethy.nutrition.core.domain.value;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Document(collection = "pal_evaluates")
public class PalEvaluate {

    @Id
    private String id;

    @Field(name = "age_group")
    private final Range ageGroup;

    private final Double sedentary;

    private final Double moderately;

    private final Double vigorous;
}
