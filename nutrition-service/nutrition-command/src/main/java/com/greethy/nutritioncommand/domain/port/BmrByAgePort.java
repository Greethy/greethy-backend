package com.greethy.nutritioncommand.domain.port;

import com.greethy.nutritioncommon.entity.BmrByAge;
import reactor.core.publisher.Mono;

public interface BmrByAgePort {

    Mono<BmrByAge> findByAgeGroup(int age);

}
