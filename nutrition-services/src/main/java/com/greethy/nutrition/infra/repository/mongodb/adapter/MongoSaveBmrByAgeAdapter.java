package com.greethy.nutrition.infra.repository.mongodb.adapter;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.domain.value.BmrByAge;
import com.greethy.nutrition.core.port.out.write.SaveBmrByAgePort;
import com.greethy.nutrition.infra.repository.mongodb.BmrByAgeRepository;
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
