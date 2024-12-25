package com.greethy.account.common.event;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class UserRegisteredEvent {

    private String id;
    private String username;
    private String email;
    private boolean emailVerified;
    private boolean enabled;

}
