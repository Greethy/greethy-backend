package com.greethy.gateway.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.greethy.gateway.config.security.jwt.JwtTokenProvider;
import com.greethy.gateway.controller.dto.request.AuthRequest;
import com.greethy.gateway.controller.dto.response.ServerTokenResponse;
import com.greethy.gateway.controller.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
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

    private final WebClient.Builder webClientBuilder;

    private final ReactiveAuthenticationManager authenticationManager;

    @ResponseBody
    @GetMapping("/test")
    public Mono<UserDetails> user(@RequestParam(name = "username_or_email") String usernameOrEmail) {
        return webClientBuilder.build()
                .get()
                .uri(uriBuilder -> uriBuilder.host("user-services").port(8085)
                        .path("/api/v1/user")
                        .queryParam("username_or_email", usernameOrEmail)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .map(userDto -> User.builder()
                        .username(userDto.getUsername())
                        .password(userDto.getPassword())
                        .roles(userDto.getRoles().toArray(new String[0]))
                        .accountExpired(!userDto.isVerified())
                        .credentialsExpired(!userDto.isVerified())
                        .disabled(!userDto.isVerified())
                        .accountLocked(!userDto.isVerified())
                        .build()
                );
    }

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
