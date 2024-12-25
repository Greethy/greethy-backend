package com.greethy.account.auth.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KeycloakCredential {

    private String type;
    private String value;
    private boolean temporary;

}
