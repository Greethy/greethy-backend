package com.greethy.user.infra.persistent.mongodb.adapter;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.user.core.port.out.DeleteUserPort;
import com.greethy.user.infra.persistent.mongodb.UserMongoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@InfrastructureAdapter("mongodb-adapter")
public class MongoDeleteUserAdapter implements DeleteUserPort {

    private final UserMongoRepository userRepository;

    @Override
    public Mono<Void> deleteById(String id) {
        return userRepository.deleteById(id);
    }
}
