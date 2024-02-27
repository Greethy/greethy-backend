package com.greethy.user.api.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greethy.user.core.domain.entity.Network;
import com.greethy.user.core.domain.entity.PersonalDetail;
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

    @JsonProperty("personal_info")
    private PersonalDetail profile;

    private Network network;

    private List<Role> roles;

}
