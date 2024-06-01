package com.greethy.userquery.infra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.greethy.usercommon.repository.mongodb")
public class MongoConfig {
}
