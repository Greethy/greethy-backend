package com.greethy.gateway.core.service;

import com.greethy.gateway.api.rest.dto.response.UserInfo;

import reactor.core.publisher.Mono;

public interface AuthService {
    Mono<UserInfo> getGoogleUserInfo(String accessToken);
}
