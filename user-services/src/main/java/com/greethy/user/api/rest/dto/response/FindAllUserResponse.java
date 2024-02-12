package com.greethy.user.api.rest.dto.response;

import com.greethy.user.infrastructure.entity.Network;
import com.greethy.user.infrastructure.entity.Profile;
import com.greethy.user.infrastructure.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FindAllUserResponse {

    private String id;

    private String username;

    private String email;

    private Profile profile;

    private Network network;

    private List<Role> roles;

}
