package com.greethy.account.domain.entity;

import com.greethy.account.domain.valueobject.AccountStatus;
import com.greethy.account.domain.valueobject.Gender;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Date;

@Data
public class Account {

    private String id;

    private String userId;

    private String image;

    private Date dateOfBirth;

    private Gender gender;

    private AccountStatus status;

    private ZonedDateTime createdAt;

    private ZonedDateTime modifiedAt;

    private String createdBy;

    private String modifiedBy;

}
