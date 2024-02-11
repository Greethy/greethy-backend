package com.greethy.user.core.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserRegisteredEvent {

    private String userId;

    private String username;

    private String email;

    private String password;

}
