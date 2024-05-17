package com.greethy.user.infra.repository.mongodb.adapter;

import com.greethy.annotation.hexagonal.DrivenAdapter;
import com.greethy.user.core.domain.entity.User;
import com.greethy.user.core.port.out.write.SaveUserPort;
import com.greethy.user.infra.repository.mongodb.UserRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
@DrivenAdapter("mongodb-save-adapter")
public class MongoSaveUserAdapter implements SaveUserPort {

    private final UserRepository userRepository;

    @Override
    public Mono<User> save(User user) {
        return userRepository.save(user);
    }
}
