package com.greethy.gateway.api.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserRegisteredResponse {

    @JsonProperty("user_id")
    private String userId;

}
