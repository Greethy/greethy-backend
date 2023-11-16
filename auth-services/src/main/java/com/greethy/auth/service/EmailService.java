package com.greethy.auth.service;

import jakarta.mail.MessagingException;

public interface EmailService {

    void sendVerificationEmail(String to) throws MessagingException;

}
