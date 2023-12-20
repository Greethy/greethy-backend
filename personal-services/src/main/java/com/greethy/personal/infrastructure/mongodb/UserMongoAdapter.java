package com.greethy.personal.infrastructure.mongodb;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.personal.domain.entity.User;
import com.greethy.personal.domain.port.outbound.CreateUserPort;
import com.greethy.personal.domain.port.outbound.DeleteUserPort;
import com.greethy.personal.domain.port.outbound.FindUserPort;
import com.greethy.personal.domain.port.outbound.UpdateUserPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@InfrastructureAdapter
@RequiredArgsConstructor
public class UserMongoAdapter
        implements CreateUserPort, FindUserPort, UpdateUserPort, DeleteUserPort {

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
}
