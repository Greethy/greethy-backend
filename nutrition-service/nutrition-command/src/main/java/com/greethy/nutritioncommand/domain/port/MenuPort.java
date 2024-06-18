package com.greethy.nutritioncommand.domain.port;

import com.greethy.nutritioncommon.entity.Menu;
import reactor.core.publisher.Mono;

public interface MenuPort {

    Mono<Menu> save(Menu menu);

}
