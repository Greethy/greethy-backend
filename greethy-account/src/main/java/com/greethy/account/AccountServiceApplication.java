package com.greethy.account;

import com.greethy.account.auth.infrastructure.adapter.KeycloakUserAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Slf4j
@RequiredArgsConstructor
@EnableConfigurationProperties
@SpringBootApplication(scanBasePackages = {"com.greethy.core", "com.greethy.account"})
public class AccountServiceApplication {

    private final KeycloakUserAdapter adapter;

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

}
