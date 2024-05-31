package com.greethy.user.domain.port;

import com.greethy.user.common.entity.Role;
import reactor.core.publisher.Mono;

public interface RolePort {

    Mono<Role> getRoleByDefaultIsTrue();

}
