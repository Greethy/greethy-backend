package com.greethy.nutritioncommon.repository;

import com.greethy.nutritioncommon.entity.BodySpec;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface BodySpecRepository extends ReactiveMongoRepository<BodySpec, String> {

    Mono<BodySpec> findFirstByUsernameOrderByCreatedAtDesc(String username);

}
