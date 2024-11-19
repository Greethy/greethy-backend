package com.greethy.account.user.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Credential {

    private String type;
    private String value;
    private boolean temporary;

}
