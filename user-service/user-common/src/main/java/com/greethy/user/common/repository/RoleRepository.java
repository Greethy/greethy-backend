package com.greethy.user.common.repository;

import com.greethy.user.common.entity.Role;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface RoleRepository extends ReactiveMongoRepository<Role, String> {

    Mono<Role> findByIsDefaultTrue();

}
