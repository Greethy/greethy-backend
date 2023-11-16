package com.greethy.auth.controller;

import com.greethy.auth.dto.EmailRequest;
import com.greethy.auth.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/email")
public class EmailController {

    private final EmailService emailService;

    @GetMapping(value = "/verify")
    public ResponseEntity<?> sendDefaultVerificationEmail(@RequestParam(name = "to") String to) throws MessagingException {
        emailService.sendVerificationEmail(to);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Email Sent !");
    }

    @PostMapping(value = "/send")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest){
        return null;
    }

}
