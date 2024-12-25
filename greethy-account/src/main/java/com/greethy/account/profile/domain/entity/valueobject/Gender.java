package com.greethy.account.profile.domain.entity.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {

    MALE("Male", 0),
    FEMALE ("Female", 1),
    OTHER ("Other", 2);

    private final String displayName;
    private final int value;


}
