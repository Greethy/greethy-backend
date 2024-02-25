package com.greethy.user.infra.persistent.mongodb;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.user.core.port.out.CheckIfExistsUserPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@InfrastructureAdapter("mongodb-check-adapter")
public class MongoCheckIfUserAdapter implements CheckIfExistsUserPort {

    private final UserMongoRepository userRepository;

    @Override
    public Boolean existsById(String id) {
        return userRepository.existsById(id)
                .block();
    }

    @Override
    public Boolean existsByUsernameOrEmail(String username, String email) {
        return userRepository.existsByUsernameOrEmail(username, email)
                .block();
    }
}
