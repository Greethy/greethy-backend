package com.greethy.nutritioncommon.entity.value;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Basal Metabolic Rate (BMR)
 *
 * @author Kien N.Thanh
 * */
@Data
@ToString
public class Bmr {

    @Field(name = "bmr_per_kg")
    private Double bmrPerKg;

    @Field(name = "bmr_per_day")
    private Double bmrPerDay; // = bmrPerKg x Kg (of user).
}
