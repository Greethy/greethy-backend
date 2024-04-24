package com.greethy.user.infra.repository.mongodb.adapter;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.user.core.port.out.read.CheckIfExistsUserPort;
import com.greethy.user.infra.repository.mongodb.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@InfrastructureAdapter("mongodb-check-adapter")
public class MongoCheckIfUserAdapter implements CheckIfExistsUserPort {

    private final UserRepository userRepository;

    @Override
    public Boolean existsById(String id) {
        return userRepository.existsById(id)
                .block();
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email)
                .block();
    }

    @Override
    public Boolean existsByUsernameOrEmail(String username, String email) {
        return userRepository.existsByUsernameOrEmail(username, email)
                .block();
    }
}
