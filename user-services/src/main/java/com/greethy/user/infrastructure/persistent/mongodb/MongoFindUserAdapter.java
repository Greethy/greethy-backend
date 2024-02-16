package com.greethy.user.infrastructure.persistent.mongodb;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.user.core.port.out.FindUserPort;
import com.greethy.user.infrastructure.entity.User;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@InfrastructureAdapter
@RequiredArgsConstructor
public class MongoFindUserAdapter implements FindUserPort {

    private final UserMongoRepository userMongoRepository;

    @Override
    public Mono<User> findById(String id) {
        return userMongoRepository.findById(id);
    }

    @Override
    public Flux<User> findAll() {
        return userMongoRepository.findAll();
    }
}
