package com.greethy.auth.controller;

import com.greethy.auth.dto.LoginRequest;
import com.greethy.auth.dto.RegisterRequest;
import com.greethy.auth.entity.Email;
import com.greethy.auth.service.AuthService;
import com.greethy.auth.service.EmailService;
import jakarta.mail.MessagingException;
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

    private final EmailService emailService;

    @GetMapping
    public String test(){
        return "Hello Auth-Services";
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> login(LoginRequest loginRequest){
        authService.authenticate(loginRequest);
        return ResponseEntity.ok(loginRequest.getUsername() + " " + loginRequest.getPassword());
    }


    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest){
        var response =  authService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping(value = "/email/send")
    public ResponseEntity<?> sendVerificationCode(@RequestParam(name = "to") String to) throws MessagingException {

        Email email = Email.builder()
                .from("knkuro00@gmail.com").to(to)
                .subject("Email Verification").body("Bla bla")
                .build();
        emailService.sendVerificationEmail(email);
        return ResponseEntity.ok("Email Sent!");
    }

    @GetMapping("/verify/token")
    public String authenticateToken(){
        return "Validating Token !!!!";
    }

}
