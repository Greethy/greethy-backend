package com.greethy.nutrition.infra.repository.mongodb.adapter;

import com.greethy.annotation.hexagonal.DrivenAdapter;
import com.greethy.nutrition.core.domain.entity.BodySpecs;
import com.greethy.nutrition.core.port.out.BodySpecsPort;
import com.greethy.nutrition.infra.repository.mongodb.BodySpecsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@DrivenAdapter("mongoBodySpecsAdapter")
public class MongoBodySpecsAdapter implements BodySpecsPort {

    private final BodySpecsRepository repository;

    @Override
    public Mono<BodySpecs> save(BodySpecs bodySpecs) {
        return repository.save(bodySpecs);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<Long> countAll() {
        return repository.count();
    }

    @Override
    public Mono<BodySpecs> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<BodySpecs> findAll() {
        return repository.findAll();
    }

    @Override
    public Flux<BodySpecs> findAllBy(Pageable pageable) {
        return repository.findAllBy(pageable);
    }

    @Override
    public Flux<BodySpecs> findAllByIds(Flux<String> ids) {
        return repository.findAllById(ids);
    }
}
