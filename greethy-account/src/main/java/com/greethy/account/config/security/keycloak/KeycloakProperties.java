package com.greethy.account.config.security.keycloak;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "keycloak")
public class KeycloakProperties {

    private String baseUrl;
    private String realm;
    private String resourcePath;
    private String grantType;
    private String clientId;
    private String clientSecret;
    private String scope;

}
