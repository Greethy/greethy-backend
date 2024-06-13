package com.greethy.usercommon.repository.mongodb;

import com.greethy.usercommon.entity.BodySpecsManagement;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodySpecManagementRepository extends ReactiveMongoRepository<BodySpecsManagement, String> {
}
