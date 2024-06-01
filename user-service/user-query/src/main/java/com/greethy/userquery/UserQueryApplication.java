package com.greethy.userquery;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication(
        scanBasePackages = {
                "com.greethy.core",
                "com.greethy.mapper",
                "com.greethy.annotation",
                "com.greethy.usercommon",
                "com.greethy.userquery",
        })
public class UserQueryApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserQueryApplication.class);
    }
}