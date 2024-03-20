package com.greethy.gateway.api.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthRequest(
        @JsonProperty("username_or_email") String usernameOrEmail,
        String password
) {}
