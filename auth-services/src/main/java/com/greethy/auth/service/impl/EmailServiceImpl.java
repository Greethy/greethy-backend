package com.greethy.auth.service.impl;

import com.greethy.auth.entity.Email;
import com.greethy.auth.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender sender;

    @Override
    public void sendVerificationEmail(String to) throws MessagingException {
        Email email = Email.builder()
                .from("knkuro00@gmail.com").to(to)
                .subject("Email Verification").body("Bla bla")
                .build();

        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setFrom(email.getFrom());
        messageHelper.setTo(email.getTo());
        messageHelper.setSubject(email.getSubject());
        messageHelper.setText(email.getBody());
        sender.send(mimeMessage);
    }

}
