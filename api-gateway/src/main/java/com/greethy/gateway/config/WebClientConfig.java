package com.greethy.gateway.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * This class is a Spring configuration class responsible for
 * setting up a WebClient.Builder with load balancing support.
 *
 * @author ThanhKien
 */
@Configuration
public class WebClientConfig {

    /**
     * Creates a WebClient.Builder bean that can be used to build
     * WebClient instances for making HTTP requests. The `@LoadBalanced`
     * annotation ensures that the WebClient supports load balancing.
     *
     * @return A WebClient.Builder instance with load balancing support.
     */
    @Bean
    @LoadBalanced
    WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

}
