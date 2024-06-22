package com.greethy.nutritionquery.domain.port;

import com.greethy.nutritioncommon.entity.BodySpec;
import reactor.core.publisher.Mono;

public interface BodySpecPort {

    Mono<BodySpec> findById(String id);

    Mono<BodySpec> findFirstByUsernameOrOrderByCreatedAtDesc(String username);
}
