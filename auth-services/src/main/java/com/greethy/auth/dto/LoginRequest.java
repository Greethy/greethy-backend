package com.greethy.auth.dto;

import lombok.Data;

/**
 *  This class represents a login request's inf, it contains information about the
 *  username and password provided by the user when attempting to log in to the system.
 *
 * @author ThanhKien
 * */
@Data
public class LoginRequest {

    private String username;

    private String password;
}
