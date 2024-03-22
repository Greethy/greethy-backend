package com.greethy.gateway.infra.config.route;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @LoadBalanced
    @Bean("loadBalanced-WebClient")
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

    @Primary
    @Bean("Default-WebClient")
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

}
