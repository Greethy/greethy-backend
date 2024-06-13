package com.greethy.usercommand.infra.adapter.mongodb;

import com.greethy.common.infra.component.annotation.DrivenAdapter;
import com.greethy.usercommand.domain.port.BodySpecsManagementPort;
import com.greethy.usercommon.entity.BodySpecsManagement;
import com.greethy.usercommon.repository.mongodb.BodySpecManagementRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@DrivenAdapter
@RequiredArgsConstructor
public class MongoBodySpecManagementAdapter implements BodySpecsManagementPort {

    private final BodySpecManagementRepository repository;

    @Override
    public Mono<BodySpecsManagement> save(BodySpecsManagement bodySpecsManagement) {
        return repository.save(bodySpecsManagement);
    }

    @Override
    public Mono<BodySpecsManagement> findById(String id) {
        return repository.findById(id);
    }
}
