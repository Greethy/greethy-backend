package com.greethy.gateway.api.rest.controller;

import com.greethy.gateway.api.rest.dto.request.AuthRequest;
import com.greethy.gateway.api.rest.dto.request.RegisterRequest;
import com.greethy.gateway.api.rest.dto.response.ServerTokenResponse;
import com.greethy.gateway.api.rest.dto.response.UserRegisteredResponse;
import com.greethy.gateway.core.exception.UserNotFoundException;
import com.greethy.gateway.core.service.AuthService;
import com.greethy.gateway.core.service.UserService;
import com.greethy.gateway.infra.config.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Value("${app.password}")
    private String password;

    private final UserService userService;

    private final AuthService authService;

    private final JwtTokenProvider tokenProvider;

    private final ReactiveAuthenticationManager authenticationManager;

    @PostMapping("/login/google")
    public Mono<ResponseEntity<?>> googleLogin(@RequestParam("access-token") String accessToken) {
        return authService
                .getGoogleUserInfo(accessToken)
                .delayElement(Duration.ofSeconds(3))
                .publishOn(Schedulers.boundedElastic())
                .filter(userInfo -> Boolean.TRUE.equals(
                        userService.checkIfUserEmailExists(userInfo.getEmail()).block()))
                .switchIfEmpty(Mono.error(
                        new UserNotFoundException(400, "")))
                .map(userInfo -> new UsernamePasswordAuthenticationToken(userInfo.getEmail(), password))
                .flatMap(authenticationManager::authenticate)
                .delayElement(Duration.ofSeconds(3))
                .map(tokenProvider::createToken)
                .map(token -> ResponseEntity.ok()
                        .headers(httpHeaders -> httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                        .body(ServerTokenResponse.builder()
                                .type("Bearer")
                                .accessToken(token)
                                .build()));
    }

    //    @PostMapping("/register/google")
    //    public Mono<ResponseEntity<?>> googleRegister(@RequestParam("access-token") String accessToken) {
    //        return authService.getGoogleUserInfo(accessToken)
    //                .publishOn(Schedulers.boundedElastic())
    //                .filter(userInfo ->
    // Boolean.FALSE.equals(userService.checkIfUserEmailExists(userInfo.getEmail()).block()))
    //                .;
    //    }

    @PostMapping("/register/greethy")
    Mono<ResponseEntity<?>> registerGreethyUser(@RequestBody Mono<RegisterRequest> request) {
        return userService
                .registerGreethyUser(request)
                .map(UserRegisteredResponse::getUsername)
                .map(tokenProvider::createToken)
                .flatMap(token -> Mono.just(ResponseEntity.ok()
                        .headers(httpHeaders -> httpHeaders.add(HttpHeaders.AUTHORIZATION, token))
                        .body(ServerTokenResponse.builder()
                                .type("Bearer")
                                .accessToken(token)
                                .build())));
    }

    @PostMapping("/login/greethy")
    Mono<ResponseEntity<?>> loginGreethyUser(@RequestBody Mono<AuthRequest> request) {
        return request.map(login -> new UsernamePasswordAuthenticationToken(login.usernameOrEmail(), login.password()))
                .flatMap(authenticationManager::authenticate)
                .map(tokenProvider::createToken)
                .map(token -> ResponseEntity.ok()
                        .headers(httpHeaders -> httpHeaders.add(HttpHeaders.AUTHORIZATION, token))
                        .body(ServerTokenResponse.builder()
                                .type("Bearer")
                                .accessToken(token)
                                .build()));
    }
}
