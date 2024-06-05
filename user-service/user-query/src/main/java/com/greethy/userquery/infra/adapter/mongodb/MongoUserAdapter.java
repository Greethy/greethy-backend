package com.greethy.userquery.infra.adapter.mongodb;

import com.greethy.annotation.hexagonal.DrivenAdapter;
import com.greethy.usercommon.entity.User;
import com.greethy.usercommon.repository.mongodb.UserRepository;
import com.greethy.userquery.domain.port.UserPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@DrivenAdapter
@RequiredArgsConstructor
public class MongoUserAdapter implements UserPort {

    private final UserRepository repository;

    @Override
    public Mono<User> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<User> findByUsernameOrEmail(String usernameOrEmail) {
        return repository.findByUsernameOrEmail(usernameOrEmail);
    }
}
