package com.greethy.personal.infrastructure.mongodb;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.personal.domain.entity.User;
import com.greethy.personal.domain.port.out.FindUserPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@InfrastructureAdapter
@RequiredArgsConstructor
public class FindUserAdapter implements FindUserPort {

    private final UserRepository userRepository;

    @Override
    public Mono<User> findById(String id) {
        return userRepository.findById(id);
    }

}
