package com.greethy.gateway.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServerTokenResponse {

    private String type;

    @JsonProperty("access_token")
    private String accessToken;

}
