package com.greethy.account.profile.domain.entity;

import com.greethy.account.profile.domain.entity.valueobject.Address;
import com.greethy.account.profile.domain.entity.valueobject.BodyIndex;
import com.greethy.account.profile.domain.entity.valueobject.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountProfile {

    private String image;
    private String displayName;
    private String description;
    private LocalDate dateOfBirth;
    private Gender gender;
    private Address address;
    private BodyIndex bodyIndex;

}
