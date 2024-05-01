package com.greethy.user.core.domain.value;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RowAffected {

    @JsonProperty("RowAffected")
    private Integer rowAffected;
}
