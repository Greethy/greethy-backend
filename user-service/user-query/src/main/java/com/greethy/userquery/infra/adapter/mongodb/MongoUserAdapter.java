package com.greethy.userquery.infra.adapter.mongodb;

import com.greethy.common.infra.component.annotation.DrivenAdapter;
import com.greethy.usercommon.entity.User;
import com.greethy.usercommon.repository.mongodb.UserRepository;
import com.greethy.userquery.domain.port.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@DrivenAdapter("mongoUserAdapter")
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

    @Override
    public Flux<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Flux<User> findByPagination(Pageable pageable) {
        return repository.findBy(pageable);
    }
}
