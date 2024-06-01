package com.greethy.usercommand.infra.adapter.mongodb;

import com.greethy.annotation.hexagonal.DrivenAdapter;
import com.greethy.usercommand.domain.port.UserPort;
import com.greethy.usercommon.entity.User;
import com.greethy.usercommon.repository.mongodb.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@DrivenAdapter("mongoUserAdapter")
public class MongoUserAdapter implements UserPort {

    private final UserRepository repository;

    @Override
    public Mono<User> save(User user) {
        return repository.save(user);
    }

    @Override
    public Mono<Boolean> existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public Mono<User> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Boolean> existsByUsernameOrEmail(String username, String email) {
        return repository.existsByUsernameOrEmail(username, email);
    }
}
