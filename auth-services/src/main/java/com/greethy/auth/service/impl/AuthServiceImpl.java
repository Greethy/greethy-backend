package com.greethy.auth.service.impl;

import com.greethy.auth.dto.LoginRequest;
import com.greethy.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager manager;

    public void authenticate(LoginRequest loginRequest) {
        String username = obtain(Optional.ofNullable(loginRequest.username()));
        String password = obtain(Optional.ofNullable(loginRequest.password()));
        System.out.println(username + " " + password);
    }

    private String obtain(Optional<String> inputString){
        return inputString.map(String::trim).orElse("");
    }

}
