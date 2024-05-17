package com.greethy.nutrition.core.port.out;

import com.greethy.nutrition.core.domain.value.BmrByAge;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BmrByAgePort {

    Mono<Void> deleteAll();

    Flux<BmrByAge> saveAll(Iterable<BmrByAge> bmrByAges);

    Mono<BmrByAge> findByAgeGroup(int age);

}
