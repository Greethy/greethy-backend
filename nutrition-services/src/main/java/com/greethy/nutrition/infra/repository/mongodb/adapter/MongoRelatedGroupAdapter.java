package com.greethy.nutrition.infra.repository.mongodb.adapter;

import com.greethy.annotation.hexagonal.DrivenAdapter;
import com.greethy.nutrition.core.domain.entity.RelatedGroup;
import com.greethy.nutrition.core.port.out.RelatedGroupPort;
import com.greethy.nutrition.infra.repository.mongodb.RelatedGroupRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@DrivenAdapter("mongoRelatedGroupAdapter")
public class MongoRelatedGroupAdapter implements RelatedGroupPort {

    private final RelatedGroupRepository repository;

    @Override
    public Mono<RelatedGroup> save(RelatedGroup relatedGroup) {
        return repository.save(relatedGroup);
    }

    @Override
    public Mono<Void> deleteAll() {
        return repository.deleteAll();
    }


}
