package com.greethy.nutrition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * The main class to start the Nutrition Application. This class initializes and runs the
 * Spring Boot application. It also specifies the base packages to scan for components.
 *
 * @author Kien N.Thanh
 */
@Slf4j
@RequiredArgsConstructor
@SpringBootApplication(scanBasePackages = {
        "com.greethy.nutrition",
        "com.greethy.core",
        "com.greethy.mapper"
})
public class NutritionApplication {

    /**
     * Main method to start the Nutrition Application.
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(NutritionApplication.class, args);
    }

}
