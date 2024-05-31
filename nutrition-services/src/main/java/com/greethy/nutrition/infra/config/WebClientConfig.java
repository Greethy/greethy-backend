package com.greethy.nutrition.infra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean("userClient")
    public WebClient webClientBuilder(@Value("${app.microservice.user-services.url}") String userServicesUrl) {
        return WebClient.builder()
                .baseUrl(userServicesUrl)
                .build();
    }
}
