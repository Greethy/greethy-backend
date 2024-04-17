package com.greethy.gateway.api.rest.controller;

import com.greethy.gateway.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/me")
public class CurrentUserController {

    private final UserService userService;

    @GetMapping("")
    Mono<ResponseEntity<?>> getCurrentUser(@AuthenticationPrincipal Mono<UserDetails> principal) {
        return principal.map(UserDetails::getUsername)
                .flatMap(userService::getCurrentUser)
                .map(currentUserResponse -> ResponseEntity.ok().body(currentUserResponse));
    }

    @GetMapping("/body-specs")
    Mono<ResponseEntity<?>> getCurrentUserBodySpecs(@RequestParam("limit") int limit,
                                                    @RequestParam("offset") int offset) {
        return null;
    }

}
