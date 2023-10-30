package com.greethy.nutrition.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class BaseEntity implements Serializable {

    @Id
    private String id;

    @CreatedDate
    @Field(name = "created_date")
    private LocalDate createdDate;
}
