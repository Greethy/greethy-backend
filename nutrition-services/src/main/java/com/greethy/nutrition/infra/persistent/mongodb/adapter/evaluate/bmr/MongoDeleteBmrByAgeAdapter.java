package com.greethy.nutrition.infra.persistent.mongodb.adapter.evaluate.bmr;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.port.out.evaluate.bmr.DeleteBmrByAgePort;
import com.greethy.nutrition.infra.persistent.mongodb.BmrByAgeRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@InfrastructureAdapter
@RequiredArgsConstructor
public class MongoDeleteBmrByAgeAdapter implements DeleteBmrByAgePort {

    private final BmrByAgeRepository bmrByAgeRepository;

    @Override
    public Mono<Void> deleteAll() {
        return bmrByAgeRepository.deleteAll();
    }
}
