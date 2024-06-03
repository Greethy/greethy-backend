package com.greethy.nutritioncommon.entity.value;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Physical Activity Level (PAL)
 *
 * @author Kien N.Thanh
 * */
@Data
@ToString
public class Pal {

    @Field(name = "activity_level")
    private String activityLevel;

    private Double value;
}
