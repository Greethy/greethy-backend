package com.greethy.gateway.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.greethy.gateway.controller.dto.UserDto;
import com.greethy.gateway.controller.dto.request.TokenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Value("${google.client-id}")
    private String googleClientId;

    private final WebClient.Builder webClientBuilder;

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
                .bodyToMono(UserDto.class)
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

    public void googleAuth(TokenRequest request) throws IOException {
        GoogleIdTokenVerifier.Builder verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList(googleClientId));
        final GoogleIdToken googleIdToken = GoogleIdToken.parse(verifier.getJsonFactory(), request.getValue());
        final GoogleIdToken.Payload payload = googleIdToken.getPayload();
    }

}
