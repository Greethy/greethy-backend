package com.greethy.user.infra.persistent.mongodb;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.user.core.domain.entity.User;
import com.greethy.user.core.port.out.*;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@InfrastructureAdapter("mongodb-create-adapter")
public class MongoSaveUserAdapter implements SaveUserPort {

    private final UserMongoRepository userRepository;

    @Override
    public Mono<User> save(User user) {
        return userRepository.save(user);
    }

}
