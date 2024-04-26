package com.greethy.user.core.port.in.query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckIfUsernameOrEmailExistsQuery {

    private String username;

    private String email;

}
