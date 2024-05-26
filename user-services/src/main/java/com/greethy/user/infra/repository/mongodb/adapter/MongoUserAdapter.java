package com.greethy.user.infra.repository.mongodb.adapter;

import com.greethy.annotation.hexagonal.DrivenAdapter;
import com.greethy.user.core.domain.entity.User;
import com.greethy.user.core.port.out.UserPort;
import com.greethy.user.infra.repository.mongodb.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@DrivenAdapter("mongoUserAdapter")
public class MongoUserAdapter implements UserPort {

    private final UserRepository repository;

    @Override
    public Mono<Boolean> existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return repository.existsByEmail(email).block();
    }

    @Override
    public Mono<Boolean> existsByUsernameOrEmail(String username, String email) {
        return repository.existsByUsernameOrEmail(username, email);
    }

    @Override
    public Mono<User> save(User user) {
        return repository.save(user);
    }

    @Override
    public Mono<User> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<User> findByUsernameOrEmail(String usernameOrEmail) {
        return repository.findByUsernameOrEmail(usernameOrEmail);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<Long> countAll() {
        return repository.count();
    }

    @Override
    public Flux<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Flux<User> findAllBy(Pageable pageable) {
        return repository.findAllBy(pageable);
    }
}
