package com.greethy.nutrition.core.domain.entity.evaluate;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class BmiEvaluate {

    @Id
    private String id;

    private String category;

    private Range range;

}
