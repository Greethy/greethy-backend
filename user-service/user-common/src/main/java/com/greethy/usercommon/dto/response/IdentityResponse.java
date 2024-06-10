package com.greethy.usercommon.dto.response;

import com.greethy.usercommon.dto.value.RoleDto;
import lombok.Data;

import java.util.List;

@Data
public class IdentityResponse {

    private String username;

    private String password;

    private List<RoleDto> roles;

}
