package com.greethy.nutritioncommon.dto.response;

import com.greethy.nutritioncommon.dto.value.BmiDto;
import com.greethy.nutritioncommon.dto.value.BmrDto;
import com.greethy.nutritioncommon.dto.value.PalDto;
import lombok.Data;

@Data
public class BodySpecResponse {

    private String id;

    private Double height;

    private Double weight;

    private Integer age;

    private Double tdee;

    private BmiDto bmi;

    private BmrDto bmr;

    private PalDto pal;

    private String goal;
}
