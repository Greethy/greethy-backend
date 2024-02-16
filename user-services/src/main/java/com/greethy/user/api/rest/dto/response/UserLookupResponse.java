package com.greethy.user.api.rest.dto.response;

import com.greethy.user.api.rest.dto.UserDto;
import com.greethy.user.infrastructure.entity.Network;
import com.greethy.user.infrastructure.entity.Profile;
import com.greethy.user.infrastructure.entity.Role;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class UserLookupResponse {

    private List<UserDto> users;

}
