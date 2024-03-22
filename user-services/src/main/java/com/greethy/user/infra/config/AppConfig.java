package com.greethy.user.infra.config;

import com.greethy.user.core.interceptor.CommandExceptionWrappingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public CommandExceptionWrappingInterceptor commandExceptionWrappingInterceptor() {
        return new CommandExceptionWrappingInterceptor();
    }

}
