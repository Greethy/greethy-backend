package com.greethy.auth.service;

import com.greethy.auth.dto.LoginRequest;
import com.greethy.auth.dto.RegisterRequest;
import com.greethy.auth.dto.RegisterResponse;

public interface AuthService {
    void authenticate(LoginRequest loginRequest);

    RegisterResponse registerUser(RegisterRequest registerRequest);
}
