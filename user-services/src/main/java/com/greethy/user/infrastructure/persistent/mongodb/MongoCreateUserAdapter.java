package com.greethy.user.infrastructure.persistent.mongodb;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.user.infrastructure.entity.User;
import com.greethy.user.core.port.out.*;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@InfrastructureAdapter("mongodb-create-adapter")
public class MongoCreateUserAdapter implements CreateUserPort {

    private final UserMongoRepository userRepository;

    @Override
    public Mono<User> create(User user) {
        return userRepository.save(user);
    }

}
