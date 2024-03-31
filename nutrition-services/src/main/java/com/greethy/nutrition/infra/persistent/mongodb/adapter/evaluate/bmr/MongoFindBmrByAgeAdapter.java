package com.greethy.nutrition.infra.persistent.mongodb.adapter.evaluate.bmr;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.domain.entity.evaluate.BmrByAge;
import com.greethy.nutrition.core.port.out.evaluate.bmr.FindBmrByAgePort;
import com.greethy.nutrition.infra.persistent.mongodb.BmrByAgeRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@InfrastructureAdapter
@RequiredArgsConstructor
public class MongoFindBmrByAgeAdapter implements FindBmrByAgePort {

    private final BmrByAgeRepository bmrByAgeRepository;

    @Override
    public Mono<BmrByAge> findByAgeGroup(int age) {
        return bmrByAgeRepository.findByAgeGroup(age);
    }
}