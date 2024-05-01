package com.greethy.gateway.api.rest.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class UserResponse {

    private String username;

    private String password;

    private boolean verified;

    private List<String> roles;
}
