package com.greethy.usercommand.domain.port;

import com.greethy.usercommon.entity.Role;
import reactor.core.publisher.Mono;

public interface RolePort {

    Mono<Role> getRoleByDefaultIsTrue();

}
