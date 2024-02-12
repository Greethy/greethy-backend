package com.greethy.user.infrastructure.mongodb;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.user.infrastructure.entity.User;
import com.greethy.user.core.port.out.*;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@InfrastructureAdapter
@RequiredArgsConstructor
public class UserMongoAdapter implements CreateUserPort, ExistsUserPort,
        FindUserPort, UpdateUserPort, DeleteUserPort {

    private final UserRepository userRepository;

    @Override
    public Mono<User> create(User user) {
        return userRepository.save(user);
    }

    @Override
    public Mono<User> update(String id, User user) {
        return userRepository.findById(id)
                .flatMap(userRepository::save);
    }

    @Override
    public Mono<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Mono<Boolean> existsById(String id) {
        return userRepository.existsById(id);
    }

    @Override
    public Mono<Boolean> existsByUsernameOrEmail(String username, String email) {
        return userRepository.existsByUsernameOrEmail(username, email);
    }
}
