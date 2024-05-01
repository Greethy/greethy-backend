package com.greethy.nutrition.core.domain.value;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RowAffected {

    @JsonProperty("Row_Affected")
    private Integer rowAffected;
}
