package com.greethy.account.infra.repository;

import com.greethy.account.domain.entity.Account;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends R2dbcRepository<Account, String> {
}
