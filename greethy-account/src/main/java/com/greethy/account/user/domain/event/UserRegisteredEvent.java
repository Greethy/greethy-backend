package com.greethy.account.user.domain.event;

import lombok.Data;

@Data
public class UserRegisteredEvent {

    private String id;
    private String username;
    private String password;
    private String email;
    private String emailVerified;
    private boolean enabled;

}
