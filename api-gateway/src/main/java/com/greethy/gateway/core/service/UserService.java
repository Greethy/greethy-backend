package com.greethy.gateway.core.service;

import com.greethy.gateway.api.rest.dto.request.RegisterRequest;
import com.greethy.gateway.api.rest.dto.response.CurrentUserResponse;
import com.greethy.gateway.api.rest.dto.response.UserRegisteredResponse;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<Boolean> checkIfUserEmailExists(@RequestParam String email);

    Mono<UserRegisteredResponse> registerGreethyUser(Mono<RegisterRequest> request);

    Mono<CurrentUserResponse> getCurrentUser(String username);
}
