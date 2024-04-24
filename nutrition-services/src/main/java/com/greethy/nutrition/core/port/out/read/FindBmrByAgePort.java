package com.greethy.nutrition.core.port.out.read;

import com.greethy.nutrition.core.domain.value.BmrByAge;
import reactor.core.publisher.Mono;

public interface FindBmrByAgePort {

    Mono<BmrByAge> findByAgeGroup(int age);

}
