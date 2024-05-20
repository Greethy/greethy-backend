package com.greethy.nutrition.infra.repository.mongodb;

import com.greethy.nutrition.core.domain.entity.RelatedGroup;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelatedGroupRepository extends ReactiveMongoRepository<RelatedGroup, String> {
}
