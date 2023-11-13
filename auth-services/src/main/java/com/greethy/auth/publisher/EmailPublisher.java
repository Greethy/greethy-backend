package com.greethy.auth.publisher;

import com.greethy.auth.entity.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailPublisher {

    private final KafkaTemplate<String, Email> kafkaTemplate;

    public void sendMessage(Email email){
        kafkaTemplate.send("email-topic", email);
    }

}
