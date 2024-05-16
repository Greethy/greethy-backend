package com.greethy.nutrition.core.domain.value;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Document(collection = "bmi_evaluates")
public class BmiEvaluate {

    @MongoId(FieldType.STRING)
    private String id;

    private final String category;

    private final Range range;

    public BmiEvaluate(String category, Range range) {
        this.category = category;
        this.range = range;
    }
}
