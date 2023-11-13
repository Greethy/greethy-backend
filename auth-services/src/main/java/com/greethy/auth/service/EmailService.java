package com.greethy.auth.service;

import com.greethy.auth.entity.Email;
import jakarta.mail.MessagingException;

public interface EmailService {

    void sendVerificationEmail(Email email) throws MessagingException;

}
