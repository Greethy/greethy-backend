package com.greethy.auth.config;

//import com.greethy.auth.filter.JwtAuthenticationFilter;
import com.greethy.auth.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${spring.application.encoder.strength}")
    private int encoderStrength;

    private final UserServiceImpl userService;

    //private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/api/v1/auth/authenticateToken", "/api/v1/auth/token").permitAll();
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
            return new ProviderManager(authenticationProvider());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        var provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        //provider.setUserDetailsService(userService);
        provider.setUserDetailsService(inMemoryUserDetailsManager());
        return provider;
    }

    /**
     * This method defines and configures an in-memory user details manager using Spring Security.
     * In-memory user details managers are typically used for testing and development purposes.
     *
     * @return An instance of InMemoryUserDetailsManager with a single user.
     */
    @Bean
    public UserDetailsService inMemoryUserDetailsManager() {
        var user = User.withUsername("test")
                .password("{noop}test123")
                .authorities("READ")
                .build();
        return new InMemoryUserDetailsManager(user);
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
