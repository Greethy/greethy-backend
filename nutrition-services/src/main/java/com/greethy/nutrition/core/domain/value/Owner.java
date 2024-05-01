package com.greethy.nutrition.core.domain.value;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
public class Owner {

    @Field(name = "user_id")
    private String userId;

    private String avatar;

    private String username;

    private String href;
}
