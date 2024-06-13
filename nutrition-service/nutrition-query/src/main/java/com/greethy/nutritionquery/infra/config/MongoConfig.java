package com.greethy.nutritionquery.infra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.greethy.nutritioncommon.repository")
public class MongoConfig {
}
