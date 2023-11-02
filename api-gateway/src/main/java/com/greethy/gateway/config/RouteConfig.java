package com.greethy.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class defines custom route's configuration for Gateway. Using Spring Cloud Gateway's RouteLocator
 * to create routing rules for directing incoming requests from client's side to private Microservice which
 * handled the requests and return a response.
 *
 * @author ThanhKien
 * */
@Configuration
public class RouteConfig {

    /**
     * This method defines and configures route rules using a RouteLocatorBuilder.
     *
     * @param builder The RouteLocatorBuilder used to construct the routing rules.
     * @return A RouteLocator that contains the defined routes.
     */
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("personal-services-router",
                        predicate -> predicate.path("/api/v*/user/**")
                                .uri("lb://personal-services"))
                .route("auth-services-router",
                        predicate -> predicate.path("api/v*/auth/**")
                                .uri("lb://auth-services"))
                .build();
    }

}
