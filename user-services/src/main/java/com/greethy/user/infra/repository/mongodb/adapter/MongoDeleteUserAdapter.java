package com.greethy.user.infra.repository.mongodb.adapter;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.user.core.port.out.write.DeleteUserPort;
import com.greethy.user.infra.repository.mongodb.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@InfrastructureAdapter("mongodb-adapter")
public class MongoDeleteUserAdapter implements DeleteUserPort {

    private final UserRepository userRepository;

    @Override
    public Mono<Void> deleteById(String id) {
        return userRepository.deleteById(id);
    }
}
