package com.greethy.account.infra.persistent;

import com.greethy.account.domain.entity.Account;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends R2dbcRepository<Account, String> {
}
