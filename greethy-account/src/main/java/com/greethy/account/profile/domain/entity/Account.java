package com.greethy.account.profile.domain.entity;

import lombok.Data;

@Data
public class Account {

    private String id;
    private String userId;
    private AccountProfile accountProfile;

}
