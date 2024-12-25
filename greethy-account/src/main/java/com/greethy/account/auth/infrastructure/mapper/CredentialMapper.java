package com.greethy.account.auth.infrastructure.mapper;

import com.greethy.account.auth.domain.entity.valueobject.Credential;
import com.greethy.account.auth.infrastructure.model.KeycloakCredential;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CredentialMapper {

    KeycloakCredential toModel(Credential credential);
    Credential toEntity(Credential credential);
}
