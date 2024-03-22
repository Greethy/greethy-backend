package com.greethy.user.infra.config;

import com.greethy.user.core.interceptor.CommandExceptionWrappingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public CommandExceptionWrappingInterceptor commandExceptionWrappingInterceptor() {
        return new CommandExceptionWrappingInterceptor();
    }

}
