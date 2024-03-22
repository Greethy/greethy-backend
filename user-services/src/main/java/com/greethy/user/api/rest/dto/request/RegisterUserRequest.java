package com.greethy.user.api.rest.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterUserRequest {

    private String id;

    private String username;

    private String email;

    private String password;

}
