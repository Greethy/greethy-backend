package com.greethy.nutritioncommon.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@Document(collection = "body_spec_management")
public class BodySpecManagement {

    @MongoId(FieldType.STRING)
    private String id;

    @Field(name = "user_id")
    private String userId;

    @Field(name = "present_body_spec_id")
    private String presentBodySpecId;

    @Field(name = "body_spec_ids")
    private List<String> bodySpecIds;

    private Double avgHeight;

    private Double avgWeight;

    @Field(name = "avg_bmi")
    private Double avgBmi;

}
