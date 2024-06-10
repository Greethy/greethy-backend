package com.greethy.nutritioncommand.infra.adapter.mongodb;

import com.greethy.common.infra.component.annotation.DrivenAdapter;
import com.greethy.nutritioncommand.domain.port.PalEvaluatePort;
import com.greethy.nutritioncommon.entity.PalEvaluate;
import com.greethy.nutritioncommon.repository.PalEvaluateRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@DrivenAdapter
@RequiredArgsConstructor
public class MongoPalEvaluateAdapter implements PalEvaluatePort {

    private final PalEvaluateRepository repository;

    @Override
    public Mono<PalEvaluate> findByAgeGroup(int age) {
        return repository.findByAgeGroup(age);
    }

}
