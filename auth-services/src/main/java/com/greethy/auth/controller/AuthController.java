package com.greethy.auth.controller;

import com.greethy.auth.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {

    @PostMapping("/token")
    public ResponseEntity<?> provideToken(@RequestBody LoginRequest loginRequest){
        System.out.println(loginRequest.username() + " " + loginRequest.password());
        return ResponseEntity.ok("testing");
    }

    @PostMapping("/register")
    public void register(){

    }

    @GetMapping("/authenticateToken")
    public String authenticateToken(){
        return "Validating Token !!!!";
    }

}
