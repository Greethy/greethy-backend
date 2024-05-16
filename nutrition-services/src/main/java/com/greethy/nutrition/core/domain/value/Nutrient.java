package com.greethy.nutrition.core.domain.value;

import lombok.Data;

@Data
public class Nutrient {

    private Mass protein;

    private Mass carbohydrate;

    private Mass lipid;

    private Mass fiber;

    private Mass iron;

    private Mass sodium;

    @Data
    public static class Mass {

        private Double value;

        private String unit;
    }
}
