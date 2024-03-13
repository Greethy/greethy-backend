package com.greethy.nutrition.core.domain.entity.specs;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The {@code Bmi} class represents a Body Mass Index (BMI) is a person's weight in kilograms divided by the square of height in meters.
 * BMI screens for weight categories that may lead to health problems, but it doesn't diagnose the body fatness or health of an individual.
 * This class encapsulates the BMI index value along with its corresponding status.
 *
 * @author Kien N.Thanh
 * */
@Data
@NoArgsConstructor
public class Bmi {

    private Double index;

    private String status;

}
