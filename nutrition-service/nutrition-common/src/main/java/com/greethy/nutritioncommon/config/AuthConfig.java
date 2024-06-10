package com.greethy.nutritioncommon.config;

import com.greethy.nutritioncommon.dto.response.IdentityResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AuthConfig {

    @Bean
    public ReactiveAuthenticationManager authenticationManager(ReactiveUserDetailsService userDetailsService) {
        return new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        return usernameOrEmail -> WebClient.builder().build()
                .get()
                .uri(builder -> builder.path("/internal/identity")
                        .queryParam("username-or-email", usernameOrEmail)
                        .build())
                .retrieve()
                .bodyToMono(IdentityResponse.class)
                .map(identity -> User.withUsername(identity.getUsername())
                        .password(identity.getPassword())
                        .accountExpired(false)
                        .credentialsExpired(false)
                        .accountLocked(false)
                        .build());
    }

}
