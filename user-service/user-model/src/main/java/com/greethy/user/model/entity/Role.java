package com.greethy.user.model.entity;

import com.greethy.user.model.entity.enums.Permission;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document(collection = "roles")
public class Role {

    private String id;

    private String name;

    private Boolean isDefault;

    private Set<Permission> permissions;

}
