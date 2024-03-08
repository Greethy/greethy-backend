package com.greethy.user;

import com.greethy.user.api.error.ExceptionWrappingHandlerInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
        "com.greethy.user",
        "com.greethy.core",
        "com.greethy.mapper"})
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public ExceptionWrappingHandlerInterceptor exceptionWrappingHandlerInterceptor() {
        return new ExceptionWrappingHandlerInterceptor();
    }

}