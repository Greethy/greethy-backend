package com.greethy.nutritioncommand.infra.adapter.mongodb;

import com.greethy.annotation.hexagonal.DrivenAdapter;
import com.greethy.nutritioncommand.domain.port.BmrByAgePort;
import com.greethy.nutritioncommon.entity.BmrByAge;
import com.greethy.nutritioncommon.repository.BmrByAgeRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@DrivenAdapter
@RequiredArgsConstructor
public class MongoBmrByAgeAdapter implements BmrByAgePort {

    private final BmrByAgeRepository repository;

    @Override
    public Mono<BmrByAge> findByAgeGroup(int age) {
        return repository.findByAgeGroup(age);
    }
}
