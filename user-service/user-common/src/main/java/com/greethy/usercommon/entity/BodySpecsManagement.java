package com.greethy.usercommon.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "body_specs_managements")
public class BodySpecsManagement {

    @MongoId(FieldType.STRING)
    private String id;

    @Field(name = "user_id")
    private String userId;

    @Field(name = "present_body_spec_id")
    private String presentBodySpecId;

    @Field(name = "body_spec_ids")
    private List<String> bodySpecIds = new ArrayList<>();

}
