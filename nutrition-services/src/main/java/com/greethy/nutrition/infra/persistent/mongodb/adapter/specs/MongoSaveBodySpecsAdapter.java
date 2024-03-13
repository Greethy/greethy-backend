package com.greethy.nutrition.infra.persistent.mongodb.adapter.specs;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.domain.entity.specs.BodySpecs;
import com.greethy.nutrition.core.port.out.specs.SaveBodySpecsPort;
import com.greethy.nutrition.infra.persistent.mongodb.BodySpecsRepository;
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
