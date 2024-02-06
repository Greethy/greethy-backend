package com.greethy.personal.infrastructure.mongodb;

import com.greethy.personal.application.domain.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * {@code UserRepository} is a Spring Data ReactiveMongoRepository interface
 * for managing {@link User} entities in a MongoDB database in a reactive way.
 * extends from {@link org.springframework.data.mongodb.repository.ReactiveMongoRepository},
 * It's providing CRUD (Create, Read, Update, Delete) operations for the {@code User} entity.
 *
 * <p>
 * Example usage:
 * <pre>
 * {@code
 * private final UserRepository userRepository;
 *
 * Flux<User> users = userRepository.findAll();
 * }
 * </pre>
 * </p>
 *
 * @see org.springframework.data.mongodb.repository.ReactiveMongoRepository
 * @see User
 * @author ThanhKien
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Mono<Boolean> existsByUsernameOrEmail(String username, String email);

}
