package com.greethy.nutrition.infra.repository.mongodb.adapter;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.domain.value.BmrByAge;
import com.greethy.nutrition.core.port.out.evaluate.bmr.FindBmrByAgePort;
import com.greethy.nutrition.infra.repository.mongodb.BmrByAgeRepository;
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
