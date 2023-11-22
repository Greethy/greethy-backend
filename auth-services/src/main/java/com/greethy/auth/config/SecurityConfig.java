package com.greethy.auth.config;

import com.greethy.auth.entrypoint.LoginFailureEntryPoint;
import com.greethy.auth.filter.JwtAuthenticationFilter;
import com.greethy.auth.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    /**
     * Strength of the BCrypt password encoder. The higher
     * the strength, the more secure but slower the encoding.
     */
    @Value("${spring.application.encoder.strength}")
    private int encoderStrength;

    private final UserServiceImpl userService;

    private final LoginFailureEntryPoint loginFailureEntryPoint;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("api/v1/auth/register", "api/v1/auth/login", "api/v1/email/**").permitAll();
                    auth.requestMatchers("api/v1/auth/token").authenticated();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandler -> exceptionHandler.authenticationEntryPoint(loginFailureEntryPoint))
                .build();
    }

    /**
     * Exposes the authentication manager as a bean for autowiring.
     *
     * @param configuration The authentication configuration.
     * @return The authentication manager.
     * @throws Exception If an error occurs while building the authentication manager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * Configures a DaoAuthenticationProvider with a custom UserDetailsService and passwordEncoder instance.
     *
     * @return The configured authentication provider.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        var provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userService);
        return provider;
    }

    /**
     * Create a PasswordEncoder object using the BCrypt algorithm to encode passwords.
     *
     * @return A PasswordEncoder instance using the BCrypt algorithm with 10 rounds of hashing.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(encoderStrength);
    }

}
