package com.greethy.nutritioncommon.config.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties(prefix = "client.api")
public class UserClientProperties {

    private String baseUrl;

    private String path;

}
