package com.greethy.auth.service;

import com.greethy.auth.dto.LoginRequest;
import com.greethy.auth.dto.LoginResponse;
import com.greethy.auth.dto.RegisterRequest;
import com.greethy.auth.dto.RegisterResponse;

public interface AuthService {
    LoginResponse authenticate(LoginRequest loginRequest);

    RegisterResponse register(RegisterRequest registerRequest);
}
