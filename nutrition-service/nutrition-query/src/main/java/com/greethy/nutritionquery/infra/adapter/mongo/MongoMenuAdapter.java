package com.greethy.nutritionquery.infra.adapter.mongo;

import com.greethy.common.infra.component.annotation.DrivenAdapter;
import com.greethy.nutritioncommon.entity.Menu;
import com.greethy.nutritioncommon.repository.MenuRepository;
import com.greethy.nutritionquery.domain.port.MenuPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@DrivenAdapter
@RequiredArgsConstructor
public class MongoMenuAdapter implements MenuPort {

    private final MenuRepository repository;

    @Override
    public Mono<Menu> findById(String id) {
        return repository.findById(id);
    }

}
