package com.greethy.nutrition.core.domain.entity.specs;

import lombok.Data;
import lombok.ToString;

/**
 * Basal Metabolic Rate (BMR)
 *
 * @author Kien N.Thanh
 * */
@Data
@ToString
public class Bmr {

    private Double bmrPerKg;

    private Double bmrPerDay; // = bmrPerKg x Kg (of user)

}
