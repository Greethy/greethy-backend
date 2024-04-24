package com.greethy.nutrition.core.port.out.read;

import com.greethy.nutrition.core.domain.value.Owner;
import reactor.core.publisher.Mono;

public interface GetUserPort {
    
    Mono<Owner> getById(String userId);
    
}
