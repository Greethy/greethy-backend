package com.greethy.common.infra.config.properties;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class JwtProperties {

    private final String iss = "accounts.greethy.com";

    private final String secretKey = "ecf1222c-88fd-47f3-988f-bdd78bade1ad";

}
