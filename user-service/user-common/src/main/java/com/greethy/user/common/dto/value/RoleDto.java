package com.greethy.user.common.dto.value;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greethy.user.common.entity.enums.Permission;
import lombok.Data;

import java.util.Set;

@Data
public class RoleDto {

    private String name;

    @JsonProperty("is_default")
    private Boolean isDefault;

    private Set<Permission> permissions;

}
