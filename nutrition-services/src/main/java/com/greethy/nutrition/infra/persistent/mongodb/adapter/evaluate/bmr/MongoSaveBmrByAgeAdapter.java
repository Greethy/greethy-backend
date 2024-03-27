package com.greethy.nutrition.infra.persistent.mongodb.adapter.evaluate.bmr;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.domain.entity.evaluate.BmrByAge;
import com.greethy.nutrition.core.port.out.evaluate.bmr.SaveBmrByAgePort;
import com.greethy.nutrition.infra.persistent.mongodb.BmrByAgeRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@InfrastructureAdapter
@RequiredArgsConstructor
public class MongoSaveBmrByAgeAdapter implements SaveBmrByAgePort {

    private final BmrByAgeRepository repository;

    @Override
    public Flux<BmrByAge> saveAll(Iterable<BmrByAge> bmrByAges) {
        return repository.saveAll(bmrByAges);
    }
}
