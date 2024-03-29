package com.greethy.nutrition.infra.persistent.mongodb.adapter.specs;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.domain.entity.specs.BodySpecs;
import com.greethy.nutrition.core.port.out.specs.FindBodySpecsPort;
import com.greethy.nutrition.infra.persistent.mongodb.BodySpecsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@InfrastructureAdapter
@RequiredArgsConstructor
public class MongoFindBodySpecsAdapter implements FindBodySpecsPort {

    private final BodySpecsRepository bodySpecsRepository;

    @Override
    public Mono<Long> countAll() {
        return bodySpecsRepository.count();
    }

    @Override
    public Mono<BodySpecs> findById(String id) {
        return bodySpecsRepository.findById(id);
    }

    @Override
    public Flux<BodySpecs> findAll() {
        return bodySpecsRepository.findAll();
    }

    @Override
    public Flux<BodySpecs> findAllBy(Pageable pageable) {
        return bodySpecsRepository.findAllBy(pageable);
    }

    @Override
    public Flux<BodySpecs> findAllByIds(Flux<String> ids) {
        return bodySpecsRepository.findAllById(ids);
    }
}
