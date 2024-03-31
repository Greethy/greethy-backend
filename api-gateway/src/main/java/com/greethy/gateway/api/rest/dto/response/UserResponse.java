package com.greethy.gateway.api.rest.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class UserResponse {

    private String username;

    private String password;

    private boolean verified;

    private List<String> roles;

}