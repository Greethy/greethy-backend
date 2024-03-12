package com.greethy.nutrition.core.domain.entity.specs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Bmi {

    private Double index;

    private String status;

}
