package com.greethy.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication(scanBasePackages = {
        "com.greethy.user.*",
        "com.greethy.mapper",
        "com.greethy.core",
        "com.greethy.annotation"
})
public class UserQueryApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserQueryApplication.class);
    }
}