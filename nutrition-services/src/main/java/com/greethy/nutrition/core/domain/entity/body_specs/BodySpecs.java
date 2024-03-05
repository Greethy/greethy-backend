package com.greethy.nutrition.core.domain.entity.body_specs;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@Document(collection = "body_specs")
public class BodySpecs {

    private String id;

    private Integer age;

    private Double height;

    private Double weight;

    private Bmi bmi;

    private Pal pal;

    private Bmr bmr;

    @Field(name = "created_at")
    private LocalDate createAt;

}
