package com.greethy.personal.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class Relative {

    @Field(name = "user_id")
    private String userId;

    private String position;

}
