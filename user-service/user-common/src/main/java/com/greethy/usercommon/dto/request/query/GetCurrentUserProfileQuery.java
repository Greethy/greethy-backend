package com.greethy.usercommon.dto.request.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetCurrentUserProfileQuery {

    private String usernameOrEmail;

}
