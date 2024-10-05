package com.greethy.account.domain.entity;

import com.greethy.core.domain.DomainEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;



@Data
@SuperBuilder
@Table("accounts")
@EqualsAndHashCode(callSuper = true)
public class Account extends DomainEntity {

    @Id
    private String id;

    @Column("user_id")
    private String userId;

}
