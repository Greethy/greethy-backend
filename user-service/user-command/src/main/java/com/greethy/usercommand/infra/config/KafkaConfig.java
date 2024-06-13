package com.greethy.usercommand.infra.config;

import com.greethy.common.domain.event.AddBodySpecToUserEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.SenderOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Configuration
    public static class KafkaConsumerConfig {

        @Value("${spring.kafka.bootstrap-servers}")
        private String bootstrapServer;

        @Value("${spring.kafka.consumer.group-id}")
        private String groupId;

        @Value("${spring.kafka.consumer.auto-offset-reset}")
        private String autoOffsetReset;

        private Map<String, Object> consumerProperties() {
            var props = new HashMap<String, Object>();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
            props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
            props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
            props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, AddBodySpecToUserEvent.class);
            props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
            return props;
        }

        @Bean
        public ReactiveKafkaConsumerTemplate<String, AddBodySpecToUserEvent> reactiveKafkaConsumerTemplate(@Value("${spring.kafka.topic}") String topic) {
            var options = ReceiverOptions.<String, AddBodySpecToUserEvent>create(consumerProperties())
                    .subscription(Collections.singletonList(topic));
            return new ReactiveKafkaConsumerTemplate<>(KafkaReceiver.create(options));
        }

    }

    @Configuration
    public static class KafkaProducerConfig {

        @Value("${spring.kafka.bootstrap-servers}")
        private String bootstrapServer;

        @Bean
        public ReactiveKafkaProducerTemplate<String, Object> reactiveKafkaProducer() {
            var props = new HashMap<String, Object>();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
            return new ReactiveKafkaProducerTemplate<>(SenderOptions.create(props));
        }
    }

}
