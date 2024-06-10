package com.greethy.nutritioncommon.dto.response;

import com.greethy.nutritioncommon.dto.value.RoleDto;
import lombok.Data;

import java.util.List;

@Data
public class IdentityResponse {

    private String username;

    private String password;

    private List<RoleDto> roles;

}