package com.greethy.nutrition.infra.repository.mongodb.adapter;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.domain.entity.BodySpecs;
import com.greethy.nutrition.core.port.out.write.SaveBodySpecsPort;
import com.greethy.nutrition.infra.repository.mongodb.BodySpecsRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@InfrastructureAdapter
@RequiredArgsConstructor
public class MongoSaveBodySpecsAdapter implements SaveBodySpecsPort {

    private final BodySpecsRepository bodySpecsRepository;

    @Override
    public Mono<BodySpecs> save(BodySpecs bodySpecs) {
        return bodySpecsRepository.save(bodySpecs);
    }
}
