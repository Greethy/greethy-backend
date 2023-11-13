package com.greethy.auth.subscriber;

import com.greethy.auth.entity.Email;
import com.greethy.auth.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSubscriber {

    private final EmailService emailService;

    @KafkaListener(topics = "email-topic", groupId = "group_id")
    public void consume(@Payload Email email) throws MessagingException {
        emailService.sendVerificationEmail(email);
    }

}
