package com.greethy.nutritioncommon.entity.value;

import lombok.Data;

@Data
public class Nutrient {

    private Measure protein;

    private Measure carbohydrate;

    private Measure lipid;

    private Measure fiber;

    private Measure iron;

    private Measure sodium;

    @Data
    public static class Measure {

        private Double value;

        private String unit;
    }
}
