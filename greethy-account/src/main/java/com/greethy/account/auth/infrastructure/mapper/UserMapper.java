package com.greethy.account.auth.infrastructure.mapper;

import com.greethy.account.auth.domain.entity.valueobject.Credential;
import com.greethy.account.auth.domain.entity.User;
import com.greethy.account.auth.infrastructure.model.KeycloakCredential;
import com.greethy.account.auth.infrastructure.model.KeycloakUser;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = CredentialMapper.class)
public interface UserMapper {

    KeycloakUser toModel(User user);
    User toEntity(KeycloakUser keycloakUser);


    List<KeycloakCredential> toKeycloakCredentials(List<Credential> credentials);

}
