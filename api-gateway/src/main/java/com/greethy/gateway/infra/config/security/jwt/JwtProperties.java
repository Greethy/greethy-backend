package com.greethy.gateway.infra.config.security.jwt;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class JwtProperties {

    private String iss;

    private String secretKey;

}
