package com.greethy.nutrition.core.domain.entity.specs;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FitnessIndexes {

    private Bmi bmi;

    private Bmr bmr;

    private Pal pal;

    public FitnessIndexes() {
        this.bmi = new Bmi();
        this.bmr = new Bmr();
        this.pal = new Pal();
    }

}
