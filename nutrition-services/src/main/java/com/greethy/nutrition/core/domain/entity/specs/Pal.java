package com.greethy.nutrition.core.domain.entity.specs;

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

    private String ActivityType;

    private Double value;

}
