package com.greethy.user.infra.repository.mongodb.adapter;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.user.core.port.out.read.CheckIfUserExistsPort;
import com.greethy.user.infra.repository.mongodb.UserRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@InfrastructureAdapter("mongodb-check-adapter")
public class MongoCheckIfUserExistsAdapter implements CheckIfUserExistsPort {

    private final UserRepository userRepository;

    @Override
    public Mono<Boolean> existsById(String id) {
        return userRepository.existsById(id);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email).block();
    }

    @Override
    public Mono<Boolean> existsByUsernameOrEmail(String username, String email) {
        return userRepository.existsByUsernameOrEmail(username, email);
    }
}
