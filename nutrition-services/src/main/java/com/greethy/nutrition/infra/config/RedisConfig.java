package com.greethy.nutrition.infra.config;

import com.greethy.nutrition.core.domain.entity.Food;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
public class RedisConfig {

    @Bean
    public ReactiveRedisTemplate<String, Food> reactiveRedisTemplate(ReactiveRedisConnectionFactory connectionFactory) {
        Jackson2JsonRedisSerializer<Food> serializer = new Jackson2JsonRedisSerializer<>(Food.class);
        RedisSerializationContext.RedisSerializationContextBuilder<String, Food> contextBuilder = RedisSerializationContext.newSerializationContext(new Jackson2JsonRedisSerializer<>(String.class));
        RedisSerializationContext<String, Food> context = contextBuilder.value(serializer).build();
        return new ReactiveRedisTemplate<>(connectionFactory, context);
    }

}
