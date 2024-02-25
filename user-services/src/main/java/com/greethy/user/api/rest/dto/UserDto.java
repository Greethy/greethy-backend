package com.greethy.user.api.rest.dto;

import com.greethy.user.core.domain.entity.Network;
import com.greethy.user.core.domain.entity.Profile;
import com.greethy.user.core.domain.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {

    private String id;

    private String username;

    private String email;

    private Profile profile;

    private Network network;

    private List<Role> roles;

}
