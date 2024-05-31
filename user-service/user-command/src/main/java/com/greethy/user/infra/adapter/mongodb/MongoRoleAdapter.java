package com.greethy.user.infra.adapter.mongodb;

import com.greethy.annotation.hexagonal.DrivenAdapter;
import com.greethy.user.domain.port.RolePort;
import com.greethy.user.common.entity.Role;
import com.greethy.user.common.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@DrivenAdapter
@RequiredArgsConstructor
public class MongoRoleAdapter implements RolePort {

    private final RoleRepository repository;

    @Override
    public Mono<Role> getRoleByDefaultIsTrue() {
        return repository.findByIsDefaultTrue();
    }
}
