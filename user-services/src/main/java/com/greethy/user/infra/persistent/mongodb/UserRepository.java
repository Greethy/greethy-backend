package com.greethy.user.infra.persistent.mongodb;

import com.greethy.user.core.domain.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * {@code UserRepository} is a Reactive Spring-Data ReactiveMongoRepository interface for
 * managing {@link User} entities in a MongoDB database in a reactive way. extends from
 * {@link org.springframework.data.mongodb.repository.ReactiveMongoRepository}, it's
 * providing default CRUD (Create, Read, Update, Delete) operations for the {@code UserAggregate} entity.
 *
 * @see org.springframework.data.mongodb.repository.ReactiveMongoRepository
 * @author KienThanh
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Mono<Boolean> existsByUsernameOrEmail(String username, String email);

    Flux<User> findAllBy(Pageable pageable);

    @Query("""
        {
            '$or' : [
                {'username' : ?0 },
                {'email' : ?0}
            ]
        }
    """)
    Mono<User> findByUsernameOrEmail(String usernameOrEmail);

    Mono<Boolean> existsByEmail(String email);

}
