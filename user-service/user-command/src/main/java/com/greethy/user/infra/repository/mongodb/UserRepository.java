package com.greethy.user.infra.repository.mongodb;

import com.greethy.user.model.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Mono<Boolean> existsByEmail(String email);

    Mono<Boolean> existsByUsernameOrEmail(String username, String email);

}
