package com.greethy.gateway.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private String username;

    private String password;

    private boolean verified;

    private List<String> roles;

}
