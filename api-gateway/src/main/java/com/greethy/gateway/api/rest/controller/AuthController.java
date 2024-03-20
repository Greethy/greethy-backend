package com.greethy.gateway.api.rest.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.greethy.gateway.api.rest.dto.request.AuthRequest;
import com.greethy.gateway.api.rest.dto.response.ServerTokenResponse;
import com.greethy.gateway.config.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Value("${google.client-id}")
    private String googleClientId;

    private final JwtTokenProvider tokenProvider;

    private final ReactiveAuthenticationManager authenticationManager;

    @GetMapping("/login/google")
    public Mono<ServerTokenResponse> googleAuth(@RequestParam(name = "client_id_token") String clientIdToken)
            throws IOException, GeneralSecurityException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList(googleClientId))
                .build();
        GoogleIdToken idToken = verifier.verify(clientIdToken);

        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);
            String email = payload.getEmail();
            boolean emailVerified = payload.getEmailVerified();
            System.out.println(email);
            System.out.println(emailVerified);

        } else {
            System.out.println("Invalid ID token.");
        }

        return null;
    }

    @PostMapping("/login/greethy")
    public Mono<ResponseEntity<?>> greethyLogin(@RequestBody Mono<AuthRequest> request) {
        return request.map(login -> new UsernamePasswordAuthenticationToken(login.usernameOrEmail(), login.password()))
                .flatMap(authenticationManager::authenticate)
                .map(tokenProvider::createToken)
                .map(token -> {
                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
                    ServerTokenResponse serverTokenResponse = ServerTokenResponse.builder()
                            .type("BEARER")
                            .accessToken(token)
                            .build();
                    return new ResponseEntity<>(serverTokenResponse, httpHeaders, HttpStatus.OK);
                });
    }

}
