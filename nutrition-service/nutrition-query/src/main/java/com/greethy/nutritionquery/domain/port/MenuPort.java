package com.greethy.nutritionquery.domain.port;

import com.greethy.nutritioncommon.entity.Menu;
import reactor.core.publisher.Mono;

public interface MenuPort {
    Mono<Menu> findById(String id);
}
