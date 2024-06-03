package com.greethy.nutritioncommon.entity;

import com.greethy.nutritioncommon.entity.value.Range;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document(collection = "bmr_by_ages")
public class BmrByAge {

    @MongoId(FieldType.STRING)
    private String id;

    @Field(name = "age_group")
    private final Range ageGroup;

    @Field(name = "bmr_per_kg")
    private final Double bmrPerKg;
}
