package com.greethy.nutrition.core.domain.value;

import lombok.Data;
import lombok.ToString;

/**
 * Physical Activity Level (PAL)
 *
 * @author Kien N.Thanh
 * */
@Data
@ToString
public class Pal {

    private String activityLevel;

    private Double value;
}
