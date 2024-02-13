package com.greethy.user.infrastructure.persistent.mongodb;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.user.core.port.out.DeleteUserPort;
import com.greethy.user.infrastructure.entity.User;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@InfrastructureAdapter("mongodb-adapter")
public class MongoDeleteUserAdapter implements DeleteUserPort {

    private final UserMongoRepository userRepository;

    @Override
    public Mono<Void> delete(User user) {
        return userRepository.delete(user);
    }

}
