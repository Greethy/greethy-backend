package com.greethy.personal.infrastructure.mongodb;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.personal.domain.entity.User;
import com.greethy.personal.domain.port.outbound.SaveUserPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@InfrastructureAdapter
@RequiredArgsConstructor
public class SaveUserAdapter implements SaveUserPort {

    private final UserRepository userRepository;

    @Override
    public Mono<User> save(User user) {
        return userRepository.save(user);
    }
}
