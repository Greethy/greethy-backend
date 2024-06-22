package com.greethy.nutritionquery.infra.adapter.mongo;

import com.greethy.common.infra.component.annotation.DrivenAdapter;
import com.greethy.nutritioncommon.entity.BodySpec;
import com.greethy.nutritioncommon.repository.BodySpecRepository;
import com.greethy.nutritionquery.domain.port.BodySpecPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@DrivenAdapter
@RequiredArgsConstructor
public class MongoBodySpecAdapter implements BodySpecPort {

    private final BodySpecRepository repository;

    @Override
    public Mono<BodySpec> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<BodySpec> findFirstByUsernameOrOrderByCreatedAtDesc(String username) {
        return repository.findFirstByUsernameOrderByCreatedAtDesc(username);
    }
}
