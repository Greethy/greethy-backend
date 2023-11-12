package com.greethy.auth.config;

import com.greethy.auth.entity.Email;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Configuration class for setting up Kafka integration in the Spring application.
 * This class is annotated with {@code @Configuration} and {@code @EnableKafka}.
 *
 * @author ThanhKien
 */
@Configuration
@EnableKafka
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private List<String> bootstrapServers;

    @Value("${spring.kafka.producer.schema-registry-url}")
    private String schemaRegistryUrl;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    /**
     * Creates and returns a configured {@code KafkaTemplate} for producing messages to Kafka.
     *
     * @return Configured {@code KafkaTemplate} instance.
     */
    @Bean
    public KafkaTemplate<String, Email> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    /**
     * Creates and returns a {@code ProducerFactory} for configuring the Kafka producer.
     *
     * @return Configured {@code ProducerFactory} instance.
     */
    @Bean
    public ProducerFactory<String, Email> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        configProps.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    /**
     * Creates and returns a {@code ConsumerFactory} for configuring the Kafka consumer.
     *
     * @return Configured {@code ConsumerFactory} instance.
     */
    @Bean
    public ConsumerFactory<String, Email> consumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        return new DefaultKafkaConsumerFactory<>(configProps);
    }
}
