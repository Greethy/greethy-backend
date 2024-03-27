package com.greethy.nutrition.core.port.out.evaluate.bmr;

import com.greethy.nutrition.core.domain.entity.evaluate.BmrByAge;
import reactor.core.publisher.Mono;

public interface FindBmrByAgePort {

    Mono<BmrByAge> findByAgeGroup(int age);

}
