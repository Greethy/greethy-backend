package com.greethy.nutritioncommand.infra.adapter.mongodb;

import com.greethy.common.infra.component.annotation.DrivenAdapter;
import com.greethy.nutritioncommand.domain.port.MenuPort;
import com.greethy.nutritioncommon.entity.Menu;
import com.greethy.nutritioncommon.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@DrivenAdapter
@RequiredArgsConstructor
public class MongoMenuAdapter implements MenuPort {

    private final MenuRepository repository;

    @Override
    public Mono<Menu> save(Menu menu) {
        return repository.save(menu);
    }

    @Override
    public Mono<Menu> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }
}
