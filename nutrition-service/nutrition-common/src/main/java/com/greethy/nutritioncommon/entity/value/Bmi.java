package com.greethy.nutritioncommon.entity.value;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The {@code Bmi} class represents the Body Mass Index (BMI), which is calculated as a person's weight in kilograms divided by the square of their height in meters.
 * BMI is used to screen for weight categories that may lead to health problems. However, it does not diagnose the level of body fatness or the health status of an individual.
 * This class encapsulates the BMI value along with its corresponding categorical status (e.g., underweight, normal, overweight, or obese).
 *
 * @author KienThanh
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bmi {

    /**
     * The BMI value, calculated as weight in kilograms divided by the square of height in meters.
     */
    private Double index;

    /**
     * The categorical status associated with the BMI value,
     * such as "Underweight", "Normal weight", "Overweight", or "Obese".
     */
    private String status;
}
