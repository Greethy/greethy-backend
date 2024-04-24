package com.greethy.user.infra.repository.mongodb.adapter;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.user.core.domain.entity.User;
import com.greethy.user.core.port.out.read.FindUserPort;
import com.greethy.user.infra.repository.mongodb.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@InfrastructureAdapter
@RequiredArgsConstructor
public class MongoFindUserAdapter implements FindUserPort {

    private final UserRepository userMongoRepository;

    @Override
    public Mono<User> findById(String id) {
        return userMongoRepository.findById(id);
    }

    @Override
    public Flux<User> findAll() {
        return userMongoRepository.findAll();
    }

    @Override
    public Flux<User> findAllBy(Pageable pageable) {
        return userMongoRepository.findAllBy(pageable);
    }

    @Override
    public Mono<User> findByUsernameOrEmail(String usernameOrEmail) {
        return userMongoRepository.findByUsernameOrEmail(usernameOrEmail);
    }

    @Override
    public Mono<Long> countAll() {
        return userMongoRepository.count();
    }
}
