package com.greethy.usercommand.domain.port;

import com.greethy.usercommon.entity.BodySpecsManagement;
import reactor.core.publisher.Mono;

public interface BodySpecsManagementPort {

    Mono<BodySpecsManagement> save(BodySpecsManagement bodySpecsManagement);

    Mono<BodySpecsManagement> findById(String id);

}
