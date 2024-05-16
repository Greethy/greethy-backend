package com.greethy.core.domain.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
public class BaseEntity {

    @Field(name = "created_at")
    protected Date createdAt;

    @Field(name = "updated_at")
    protected Date updatedAt;

}
