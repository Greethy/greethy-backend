package com.greethy.core.domain;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Column;

import java.time.ZonedDateTime;

@Data
@SuperBuilder
public class DomainEntity {

    @Column("created_by")
    private String createdBy;
    @Column("modified_by")
    private String modifiedBy;
    @Column("created_at")
    private ZonedDateTime createdAt;
    @Column("modified_at")
    private ZonedDateTime modifiedAt;

}
