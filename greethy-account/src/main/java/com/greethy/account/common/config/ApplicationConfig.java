package com.greethy.account.common.config;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public EmailValidator emailValidator() {
        return EmailValidator.getInstance();
    }

}
