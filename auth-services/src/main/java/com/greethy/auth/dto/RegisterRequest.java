package com.greethy.auth.dto;

import lombok.Data;

@Data
public class RegisterRequest {

    private String otp;

    private String username;

    private String email;

    private String password;

}
