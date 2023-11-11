package com.greethy.auth.repository;

import com.greethy.auth.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing User entities in MongoDB.
 * This interface extends {@code MongoRepository} and provides
 * additional methods for querying and managing User entities.
 *
 * @author ThanhKien
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user to be retrieved.
     * @return An {@code Optional} containing the user with the
     * specified username, if present.
     */
    Optional<User> findByUsername(String username);

    /**
     * Checks if a user with the given username or email exists in the repository.
     *
     * @param username The username to check.
     * @param email    The email to check.
     * @return {@code true} if a user with the provided username or email exists.
     */
    Boolean existsByUsernameOrEmail(String username, String email);
}
