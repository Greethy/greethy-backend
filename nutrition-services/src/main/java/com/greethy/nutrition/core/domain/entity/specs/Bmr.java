package com.greethy.nutrition.core.domain.entity.specs;

import lombok.Data;

/**
 * Basal Metabolic Rate (BMR)
 *
 * @author Kien N.Thanh
 * */
@Data
public class Bmr {

    private Double bmrPerKg;

    private Double bmrPerDay;

}
