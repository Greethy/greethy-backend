package com.greethy.auth.service.impl;

import com.greethy.auth.dto.LoginRequest;
import com.greethy.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager manager;

    public void login(LoginRequest request) {
        var unauthenticatedToken = UsernamePasswordAuthenticationToken.unauthenticated(request.getUsername(), request.getPassword());
        manager.authenticate(unauthenticatedToken);



    }

}
