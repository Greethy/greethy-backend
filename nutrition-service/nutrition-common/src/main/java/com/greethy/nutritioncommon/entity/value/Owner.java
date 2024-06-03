package com.greethy.nutritioncommon.entity.value;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class Owner {

    @Field(name = "user_id")
    private String userId;

    private String avatar;

    private String username;

    private String href;
}
