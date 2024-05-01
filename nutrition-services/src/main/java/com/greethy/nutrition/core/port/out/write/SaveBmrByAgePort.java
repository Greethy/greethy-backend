package com.greethy.nutrition.core.port.out.write;

import com.greethy.nutrition.core.domain.value.BmrByAge;

import reactor.core.publisher.Flux;

public interface SaveBmrByAgePort {

    Flux<BmrByAge> saveAll(Iterable<BmrByAge> bmrByAges);
}
