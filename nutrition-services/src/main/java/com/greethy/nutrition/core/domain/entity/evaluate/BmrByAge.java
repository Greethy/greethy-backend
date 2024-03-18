package com.greethy.nutrition.core.domain.entity.evaluate;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "bmr_table")
public class BmrByAge {

    @Id
    private String id;

    @Field(name = "age_group")
    private final Range ageGroup;

    @Field(name = "bmr_per_kg")
    private final Double bmrPerKg;
}
