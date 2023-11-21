package com.greethy.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;


@Setter
@Builder
@AllArgsConstructor
public class RegisterResponse {

    @JsonProperty("user_id")
    private String id;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

}
