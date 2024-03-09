package com.greethy.user.infra.config;

import com.greethy.user.api.error.interceptor.ExceptionWrappingHandlerInterceptor;
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
    public ExceptionWrappingHandlerInterceptor exceptionWrappingHandlerInterceptor() {
        return new ExceptionWrappingHandlerInterceptor();
    }

}
