package com.greethy.usercommand.infra.config;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.SenderOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Setter
    @Configuration
    @RequiredArgsConstructor
    @ConfigurationProperties(prefix = "spring.kafka.consumer")
    public static class KafkaConsumerConfig {

        private String bootstrapServer;

        private String groupId;

        private String autoOffsetReset;

        @Bean
        public ReceiverOptions<String, Object> receiverOptions(@Value("${spring.kafka.consumer.topic}") String topic) {
            Map<String, Object> consumerProps = new HashMap<>();
            consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
            consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
            consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
            consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

            return ReceiverOptions.<String, Object>create(consumerProps)
                    .subscription(Collections.singletonList(topic));
        }

        @Bean
        public ReactiveKafkaConsumerTemplate<String, Object> reactiveKafkaConsumerTemplate(ReceiverOptions<String, Object> receiverOptions) {
            return new ReactiveKafkaConsumerTemplate<>(receiverOptions);
        }

    }

    @Setter
    @Configuration
    @RequiredArgsConstructor
    @ConfigurationProperties(prefix = "spring.kafka.producer")
    public static class KafkaProducerConfig {

        private String bootstrapServer;

        @Bean
        public ReactiveKafkaProducerTemplate<String, Object> reactiveKafkaProducer() {

            Map<String, Object> props = new HashMap<>();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
            return new ReactiveKafkaProducerTemplate<>(SenderOptions.create(props));
        }
    }

}
