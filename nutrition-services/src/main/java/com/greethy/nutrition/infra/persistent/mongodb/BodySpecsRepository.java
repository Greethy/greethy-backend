package com.greethy.nutrition.infra.persistent.mongodb;

import com.greethy.nutrition.core.domain.entity.specs.BodySpecs;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BodySpecsRepository extends ReactiveMongoRepository<BodySpecs, String> {

    Flux<BodySpecs> findAllBy(Pageable pageable);

}