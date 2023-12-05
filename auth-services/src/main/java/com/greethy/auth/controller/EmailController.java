package com.greethy.auth.controller;

import com.greethy.auth.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/email")
public class EmailController {

    private final EmailService emailService;

    @GetMapping(value = "/otp")
    public ResponseEntity<?> sendDefaultVerificationEmail(@RequestParam String emailTo)
            throws MessagingException, NoSuchAlgorithmException {
        emailService.sendVerificationCode(emailTo);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Mail Sent !");
    }

}
