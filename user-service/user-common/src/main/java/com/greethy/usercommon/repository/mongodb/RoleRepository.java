package com.greethy.usercommon.repository.mongodb;

import com.greethy.usercommon.entity.Role;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface RoleRepository extends ReactiveMongoRepository<Role, String> {

    Mono<Role> findByIsDefaultTrue();

}
