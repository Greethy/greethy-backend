package com.greethy.gateway.api.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserInfo {

    private String azp;

    private String aud;

    private String sub;

    private String scope;

    private String exp;

    @JsonProperty("expires_in")
    private String expiresIn;

    private String email;

    @JsonProperty("email_verified")
    private boolean verified;
}
