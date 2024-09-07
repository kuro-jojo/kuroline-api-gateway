package com.kuro.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KurolineApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(KurolineApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        // adding 2 rotes to first microservice as we need to log request body if method is POST
        return builder.routes()
                .route(p -> p
                        .path("/api/v1/users/**")
                        .uri("lb://KUROLINE-USERS-MS"))
                .route(p -> p
                        .path("/api/v1/chat/**")
                        .uri("lb://KUROLINE-CHAT-MS"))
                .route(p -> p
                        .path("/ws/v1/chat/**")
                        .uri("lb:ws://KUROLINE-CHAT-MS"))
                .build();
    }


}
