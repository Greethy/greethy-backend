package com.greethy.usercommon.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GorseResponse {

    @JsonProperty("RowAffected")
    private Integer rowAffected;

}
