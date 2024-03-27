package com.greethy.nutrition.core.port.out.evaluate.bmr;

import reactor.core.publisher.Mono;

public interface DeleteBmrByAgePort {

    Mono<Void> deleteAll();

}
