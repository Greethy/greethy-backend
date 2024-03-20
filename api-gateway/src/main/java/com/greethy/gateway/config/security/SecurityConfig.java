package com.greethy.gateway.config.security;

import com.greethy.gateway.controller.dto.response.UserResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private static final String[] AUTH_WHITELIST = {
            "/test",
            "/eureka/**", "/actuator/**", "/api/v1/auth/**",
            "/swagger-resources/**", "/swagger-ui.html", "/webjars/**", "/v3/api-docs/**"
    };

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http,
                                                         ReactiveAuthenticationManager authenticationManager) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(ServerHttpSecurity.CorsSpec::disable)
                .authenticationManager(authenticationManager)
                .authorizeExchange(exchange -> exchange.pathMatchers(AUTH_WHITELIST).permitAll()
                                .anyExchange().authenticated())
                .build();
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService(WebClient.Builder webClientBuilder) {
        return usernameOrEmail -> webClientBuilder.build()
                .get().uri(uriBuilder -> uriBuilder.host("user-services").port(8085)
                        .path("/api/v1/user")
                        .queryParam("username_or_email", usernameOrEmail)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .map(response -> User.builder()
                        .username(response.getUsername())
                        .password(response.getPassword())
                        .roles(response.getRoles().toArray(new String[0]))
                        .accountExpired(response.isVerified())
                        .credentialsExpired(response.isVerified())
                        .disabled(response.isVerified())
                        .accountLocked(response.isVerified())
                        .build()
                );
    }

    @Bean
    ReactiveAuthenticationManager authenticationManager(ReactiveUserDetailsService userDetailsService,
                                                        PasswordEncoder passwordEncoder) {
        var authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
        authenticationManager.setPasswordEncoder(passwordEncoder);
        return authenticationManager;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}
