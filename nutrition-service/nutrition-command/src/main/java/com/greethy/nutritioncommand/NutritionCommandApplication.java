package com.greethy.nutritioncommand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "com.greethy.core",
                "com.greethy.mapper",
                "com.greethy.annotation",
                "com.greethy.nutritioncommon",
                "com.greethy.nutritioncommand"
})
public class NutritionCommandApplication {

    public static void main(String[] args) {
        SpringApplication.run(NutritionCommandApplication.class);
    }
}