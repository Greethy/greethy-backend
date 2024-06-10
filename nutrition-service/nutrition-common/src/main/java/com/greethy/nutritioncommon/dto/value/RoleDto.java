package com.greethy.nutritioncommon.dto.value;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RoleDto {

    private String name;

    @JsonProperty("is_default")
    private Boolean isDefault;


}
