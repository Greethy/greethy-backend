package com.greethy.user.infrastructure.mongodb;

import com.greethy.user.infrastructure.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * {@code UserRepository} is a Reactive Spring-Data ReactiveMongoRepository interface for
 * managing {@link User} entities in a MongoDB database in a reactive way. extends from
 * {@link org.springframework.data.mongodb.repository.ReactiveMongoRepository}, it's
 * providing default CRUD (Create, Read, Update, Delete) operations for the {@code User} entity.
 *
 * @see org.springframework.data.mongodb.repository.ReactiveMongoRepository
 * @author KienThanh
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Mono<Boolean> existsByUsernameOrEmail(String username, String email);

}
