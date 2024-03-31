package com.greethy.user.core.port.in.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindUserByUsernameOrEmailQuery {

    private String usernameOrEmail;

}