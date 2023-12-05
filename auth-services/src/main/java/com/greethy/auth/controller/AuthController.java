package com.greethy.auth.controller;

import com.greethy.auth.dto.LoginRequest;
import com.greethy.auth.dto.RegisterRequest;
import com.greethy.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> login(LoginRequest loginRequest){
        var response = authService.authenticate(loginRequest);
        return ResponseEntity.ok(response);
    }


    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest){
        var response = authService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/token")
    public String authenticateToken(){
        return "Validating Token !!!!";
    }

}
