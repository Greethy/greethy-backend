package com.greethy.user.api.rest.dto.response;

import com.greethy.user.api.rest.dto.UserDto;
import lombok.*;

import java.util.List;

@Data
@Builder
public class UserLookupResponse {

    private List<UserDto> users;

}
