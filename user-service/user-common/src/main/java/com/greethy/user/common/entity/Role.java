package com.greethy.user.common.entity;

import com.greethy.user.common.entity.enums.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Set;

@Data
@AllArgsConstructor
@Document(collection = "roles")
public class Role {

    @MongoId
    private String id;

    private String name;

    @Field(name = "is_default")
    private Boolean isDefault;

    private Set<Permission> permissions;

}
