package com.greethy.auth.service;

import jakarta.mail.MessagingException;

import java.security.NoSuchAlgorithmException;

public interface EmailService {

    void sendVerificationCode(String emailTo) throws MessagingException, NoSuchAlgorithmException;

}
