//package com.greethy.user.infrastructure.config;
//
//import com.mongodb.ServerAddress;
//import com.mongodb.client.MongoClient;
//import org.axonframework.eventhandling.tokenstore.TokenStore;
//import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
//import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
//import org.axonframework.extensions.mongo.DefaultMongoTemplate;
//import org.axonframework.extensions.mongo.MongoTemplate;
//import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
//import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoFactory;
//import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoSettingsFactory;
//import org.axonframework.extensions.mongo.eventsourcing.tokenstore.MongoTokenStore;
//import org.axonframework.serialization.Serializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Collections;
//
//@Configuration
//public class AxonConfig {
//
//    @Value("${spring.data.mongodb.host:localhost}")
//    private String mongoHost;
//
//    @Value("${spring.data.mongodb.port:27017}")
//    private int mongoPort;
//
//    @Value("${spring.data.mongodb.database}")
//    private String mongoDatabase;
//
//    @Bean
//    public MongoClient mongoClient() {
//        var mongoFactory = new MongoFactory();
//        var mongoSettingsFactory = new MongoSettingsFactory();
//        var serverAddresses = Collections.singletonList(new ServerAddress(mongoHost, mongoPort));
//        mongoSettingsFactory.setMongoAddresses(serverAddresses);
//        mongoFactory.setMongoClientSettings(mongoSettingsFactory.createMongoClientSettings());
//
//        return mongoFactory.createMongo();
//    }
//
//    @Bean
//    public MongoTemplate axonMongoTemplate() {
//        return DefaultMongoTemplate.builder()
//                .mongoDatabase(mongoClient(), mongoDatabase)
//                .build();
//    }
//
//    @Bean
//    public TokenStore tokenStore(Serializer serializer) {
//        return MongoTokenStore.builder()
//                .mongoTemplate(axonMongoTemplate())
//                .serializer(serializer)
//                .build();
//    }
//
//    @Bean
//    public EventStorageEngine eventStorageEngine(MongoClient mongoClient) {
//        return MongoEventStorageEngine.builder()
//                .mongoTemplate(DefaultMongoTemplate.builder()
//                        .mongoDatabase(mongoClient)
//                        .build())
//                .build();
//    }
//
//    @Bean
//    public EmbeddedEventStore eventStore(EventStorageEngine storageEngine) {
//        return EmbeddedEventStore.builder()
//                .storageEngine(storageEngine)
//                .build();
//    }
//
//}
