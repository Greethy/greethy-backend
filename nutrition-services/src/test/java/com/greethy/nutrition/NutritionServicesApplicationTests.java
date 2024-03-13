package com.greethy.nutrition;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(
        webEnvironment = RANDOM_PORT,
        properties = {"""
            spring.data.mongodb.host=localhost
            spring.data.mongodb.port=27017
            spring.data.mongodb.database=GreethyNutritionDB
            """
        }
)
class NutritionServicesApplicationTests {

    @Test
    void contextLoads() {
    }

}
