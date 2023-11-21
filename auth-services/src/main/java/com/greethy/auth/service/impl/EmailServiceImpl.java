package com.greethy.auth.service.impl;

import com.greethy.auth.entity.Mail;
import com.greethy.auth.service.EmailService;
import com.greethy.auth.utility.OtpUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void sendVerificationCode(String emailTo) throws MessagingException, NoSuchAlgorithmException {

        String otp = OtpUtil.generateOtp();
        redisTemplate.opsForValue().set(emailTo, otp, Duration.ofMinutes(5));

        String mailBody = "<p> Hi, " + emailTo + ", </p>"+
                "<p>Thank you for registering with us," +
                "Your code is: " + otp +
                " .Please, Enter your code to complete your registration" +
                "<p> Thank you <br> Users Registration Portal Service";

        Mail mail = Mail.builder()
                .from("knkuro00@gmail.com").to(emailTo)
                .subject("Mail Verification").body(mailBody)
                .build();

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setFrom(mail.getFrom());
        messageHelper.setTo(mail.getTo());
        messageHelper.setSubject(mail.getSubject());
        messageHelper.setText(mail.getBody());
        mailSender.send(mimeMessage);
    }

}
