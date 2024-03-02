package com.greethy.nutrition.core.domain.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Document(collection = "body_specs")
public class BodySpec {

    private String id;

    private Integer age;

    private Double height;

    private Double weight;

    @Field(name = "created_at")
    private LocalDateTime createAt;

}
