package com.greethy.user.infra.config;

import io.gorse.gorse4j.Gorse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GorseConfig {

    @Value("${gorse.endpoint}")
    private String endpoint;

    @Value("${gorse.api-key}")
    private String apiKey;

    @Bean
    public Gorse gorseClient() {
        return new Gorse(endpoint, apiKey);
    }

}
