package com.greethy.nutritioncommon.entity;

import com.greethy.common.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Data
@Document(collection = "foods")
@EqualsAndHashCode(callSuper = true)
public class Food extends BaseEntity {

    @Id
    private String id;

    private String name;

    @Field(name = "total_calories")
    private Integer totalCalories;

}
