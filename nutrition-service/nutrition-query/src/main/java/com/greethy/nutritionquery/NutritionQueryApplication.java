package com.greethy.nutritionquery;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication(
        scanBasePackages = {
                "com.greethy.common",
                "com.greethy.nutritioncommon",
                "com.greethy.nutritionquery"
        })
public class NutritionQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(NutritionQueryApplication.class);
    }

}