package com.greethy.gateway.api.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RowAffected {

    @JsonProperty("RowAffected")
    private int rowAffected;

}
