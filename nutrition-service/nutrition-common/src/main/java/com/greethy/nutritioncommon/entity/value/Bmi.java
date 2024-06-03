package com.greethy.nutritioncommon.entity.value;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The {@code Bmi} class represents a Body Mass Index (BMI) is a person's weight in kilograms divided by the square of height in meters.
 * BMI screens for weight categories that may lead to health problems, but it doesn't diagnose the body fatness or health of an individual.
 * This class encapsulates the BMI index value along with its corresponding status.
 *
 * @author Kien N.Thanh
 * */
@Data
@ToString
@NoArgsConstructor
public class Bmi {

    private Double index;

    private String status;
}
