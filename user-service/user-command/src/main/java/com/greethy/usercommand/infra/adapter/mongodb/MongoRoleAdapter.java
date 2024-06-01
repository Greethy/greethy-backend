package com.greethy.usercommand.infra.adapter.mongodb;

import com.greethy.annotation.hexagonal.DrivenAdapter;
import com.greethy.usercommand.domain.port.RolePort;
import com.greethy.usercommon.entity.Role;
import com.greethy.usercommon.repository.mongodb.RoleRepository;
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
