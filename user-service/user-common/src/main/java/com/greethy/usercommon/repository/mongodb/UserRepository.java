package com.greethy.usercommon.repository.mongodb;

import com.greethy.usercommon.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Mono<Boolean> existsByEmail(String email);

    Mono<Boolean> existsByUsernameOrEmail(String username, String email);

    Mono<User> findByUsername(String username);

    @Query("""
           {
                '$or' : [
            		{'username' : {$eq: ?0 }},
            		{'email' : {$eq: ?0 }}
            	]
           }

           """)
    Mono<User> findByUsernameOrEmail(String usernameOrEmail);

    Flux<User> findBy(Pageable pageable);

}
