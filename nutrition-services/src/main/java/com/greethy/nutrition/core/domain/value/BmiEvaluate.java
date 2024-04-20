package com.greethy.nutrition.core.domain.value;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@Document(collection = "bmi_evaluates")
public class BmiEvaluate {

    @Id
    private String id;

    private final String category;

    private final Range range;

    public BmiEvaluate(String category, Range range) {
        this.category = category;
        this.range = range;
    }

}
