package com.greethy.account.domain.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Table("account_profile")
public class AccountProfile {

    private Long id;

    @Column("image_url")
    private String imageUrl;

    @Column("date_of_birth")
    private Date dateOfBirth;

}
