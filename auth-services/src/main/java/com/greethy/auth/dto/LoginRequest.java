package com.greethy.auth.dto;

/**
 *  This record represents a login request's inf, it contains information about the
 *  username and password provided by the user when attempting to log in to the system.
 *
 * @author ThanhKien
 * */
public record LoginRequest (String username, String password) {

}
