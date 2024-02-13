package com.greethy.user.infrastructure.persistent.mongodb;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.user.core.port.out.CheckIfExistsUserPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@InfrastructureAdapter("mongodb-check-adapter")
public class MongoCheckIfUserAdapter implements CheckIfExistsUserPort {

    private final UserMongoRepository userRepository;

    @Override
    public Mono<Boolean> existsById(String id) {
        return userRepository.existsById(id);
    }

    @Override
    public Mono<Boolean> existsByUsernameOrEmail(String username, String email) {
        return userRepository.existsByUsernameOrEmail(username, email);
    }
}
