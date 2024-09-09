package com.greethy.nutritioncommon.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greethy.nutritioncommon.dto.value.MealMenuDto;
import lombok.Data;

import java.util.List;

@Data
public class MenuResponse {

    private String id;

    private String name;

    private List<String> labels;

    @JsonProperty("owned_by")
    private String ownedBy;

    @JsonProperty("menu_type")
    private List<String> menuType;

    private List<MealMenuDto> meals;

}
