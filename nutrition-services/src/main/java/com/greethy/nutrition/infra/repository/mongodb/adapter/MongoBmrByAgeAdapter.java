package com.greethy.nutrition.infra.repository.mongodb.adapter;

import com.greethy.annotation.hexagonal.DrivenAdapter;
import com.greethy.nutrition.core.domain.value.BmrByAge;
import com.greethy.nutrition.core.port.out.BmrByAgePort;
import com.greethy.nutrition.infra.repository.mongodb.BmrByAgeRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@DrivenAdapter("mongoBmrByAgeAdapter")
public class MongoBmrByAgeAdapter implements BmrByAgePort {

    private final BmrByAgeRepository repository;

    @Override
    public Mono<Void> deleteAll() {
        return repository.deleteAll();
    }

    @Override
    public Flux<BmrByAge> saveAll(Iterable<BmrByAge> bmrByAges) {
        return repository.saveAll(bmrByAges);
    }

    @Override
    public Mono<BmrByAge> findByAgeGroup(int age) {
        return repository.findByAgeGroup(age);
    }
}
