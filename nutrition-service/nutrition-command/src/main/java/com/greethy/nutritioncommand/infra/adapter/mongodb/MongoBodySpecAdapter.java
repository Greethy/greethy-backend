package com.greethy.nutritioncommand.infra.adapter.mongodb;

import com.greethy.annotation.hexagonal.DrivenAdapter;
import com.greethy.nutritioncommand.domain.port.BodySpecPort;
import com.greethy.nutritioncommon.entity.BodySpec;
import com.greethy.nutritioncommon.repository.BodySpecRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@DrivenAdapter
@RequiredArgsConstructor
public class MongoBodySpecAdapter implements BodySpecPort {

    private final BodySpecRepository repository;


    @Override
    public Mono<BodySpec> save(BodySpec bodySpec) {
        return repository.save(bodySpec);
    }
}
