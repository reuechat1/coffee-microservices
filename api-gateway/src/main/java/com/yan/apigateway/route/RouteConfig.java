package com.yan.apigateway.route;

import com.yan.apigateway.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(
                        "account-service",
                        p -> p
                                .path("/api/account/**")
                                .filters(f -> f.filter(filter))
                                .uri("lb://account-service"))
                .route(
                        "auth-service",
                        p -> p
                                .path("/api/auth/**")
                                .filters(f -> f.filter(filter))
                                .uri("lb://auth-service"))
                .route(
                        "menu-service",
                        p -> p
                                .path("/api/menu/**")
                                .filters(f -> f.filter(filter))
                                .uri("lb://menu-service"))
                .route(
                        "order-service",
                        p -> p
                                .path("/api/order/**")
                                .filters(f -> f.filter(filter))
                                .uri("lb://order-service"))
                .build();
    }
}
