package com.greethy.nutritioncommon.entity;

import com.greethy.nutritioncommon.entity.value.Range;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@ToString
@Document(collection = "pal_evaluates")
public class PalEvaluate {

    @MongoId(FieldType.STRING)
    private String id;

    @Field(name = "age_group")
    private final Range ageGroup;

    private final Double sedentary;

    private final Double moderately;

    private final Double vigorous;
}
