package com.greethy.nutrition;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
        webEnvironment = RANDOM_PORT,
        properties = {
            """
			spring.data.mongodb.host=localhost
			spring.data.mongodb.port=27017
			spring.data.mongodb.database=GreethyNutritionDB
			"""
        })
class NutritionServicesApplicationTests {

    @Test
    void contextLoads() {}
}
