package com.greethy.nutrition.infra.repository.mongodb.adapter;

import org.springframework.data.domain.Pageable;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.domain.entity.BodySpecs;
import com.greethy.nutrition.core.port.out.read.FindBodySpecsPort;
import com.greethy.nutrition.infra.repository.mongodb.BodySpecsRepository;

import lombok.RequiredArgsConstructor;
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
