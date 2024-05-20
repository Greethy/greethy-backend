package com.greethy.nutrition.core.port.out;

import com.greethy.nutrition.core.domain.entity.RelatedGroup;
import reactor.core.publisher.Mono;

public interface RelatedGroupPort {

    Mono<RelatedGroup> save(RelatedGroup relatedGroup);

    Mono<Void> deleteAll();

}
