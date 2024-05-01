package com.greethy.gateway.api.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.greethy.gateway.api.rest.dto.response.RowAffected;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
public class TestController {

    private final WebClient.Builder webClientBuilder;

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<?>> test(@PathVariable("id") String id) {
        return webClientBuilder
                .baseUrl("http://localhost:8088")
                .build()
                .delete()
                .uri("/api/user/" + id)
                .header("X-API-Key", "")
                .retrieve()
                .bodyToMono(RowAffected.class)
                .map(rowAffected -> ResponseEntity.ok().body(rowAffected));
    }
}
