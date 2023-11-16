package com.greethy.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Configuration class for setting up a connection to Redis using Lettuce library.
 *
 * @author ThanhKien
 */
@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    /**
     * Creates a LettuceConnectionFactory bean for connecting to a Redis server.
     *
     * @return LettuceConnectionFactory bean configured with the Redis host and port.
     */
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        var configuration = new RedisStandaloneConfiguration(redisHost, redisPort);
        return new LettuceConnectionFactory(configuration);
    }

    /**
     * Creates a primary RedisTemplate with key's Object and value's Object in bean
     * for interacting with Redis data.
     *
     * @param redisConnectionFactory The connection factory for the RedisTemplate.
     * @return RedisTemplate bean configured with the provided connection factory.
     */
    @Bean
    @Primary
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        var template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

}
