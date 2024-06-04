package com.greethy.core.infra.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "application.jwt")
public class JwtProperties {

    private String iss;

    private String secretKey;

}
