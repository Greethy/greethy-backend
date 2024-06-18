package com.greethy.nutritioncommon.repository;

import com.greethy.nutritioncommon.entity.Menu;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends ReactiveMongoRepository<Menu, String> {
}
