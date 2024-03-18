package com.greethy.user.core.port.in.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FindUserByUsernameOrEmailQuery {

    private String usernameOrEmail;

}
