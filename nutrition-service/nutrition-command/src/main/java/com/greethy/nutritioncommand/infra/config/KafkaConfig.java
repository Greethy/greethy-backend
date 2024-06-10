package com.greethy.nutritioncommand.infra.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;
import reactor.kafka.sender.SenderOptions;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Configuration
    public static class KafkaProducerConfig {

        @Value("${spring.kafka.bootstrap-servers}")
        private String bootstrapServer;

        @Bean
        public SenderOptions<String, Object> senderOptions() {
            Map<String, Object> props = new HashMap<>();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
            props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
            return SenderOptions.create(props);
        }

        @Bean
        public ReactiveKafkaProducerTemplate<String, Object> producerTemplate(SenderOptions<String, Object> options) {
            return new ReactiveKafkaProducerTemplate<>(options);
        }

    }

}
