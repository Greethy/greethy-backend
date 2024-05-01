package com.greethy.gateway.core.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.greethy.gateway.api.rest.dto.response.UserInfo;
import com.greethy.gateway.core.exception.InvalidAccessTokenException;
import com.greethy.gateway.core.service.AuthService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final WebClient.Builder webClientBuilder;

    @Override
    public Mono<UserInfo> getGoogleUserInfo(final String accessToken) {
        return webClientBuilder
                .build()
                .get()
                .uri("https://www.googleapis.com/oauth2/v3/tokeninfo", uriBuilder -> uriBuilder
                        .queryParam("accessToken", accessToken)
                        .build())
                .retrieve()
                .bodyToMono(UserInfo.class)
                .switchIfEmpty(Mono.error(
                        new InvalidAccessTokenException(HttpStatus.BAD_REQUEST.value(), "Invalid Google AccessToken")));
    }
}
