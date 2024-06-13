package com.greethy.nutritioncommon.dto.request;

import com.greethy.nutritioncommon.entity.enums.Group;
import com.greethy.nutritioncommon.entity.enums.Meal;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateFoodRequest {

    @NotBlank(message = "")
    private String name;

    private Meal meal;

    private Group group;

    private String recipe;

    private String tips;

}
