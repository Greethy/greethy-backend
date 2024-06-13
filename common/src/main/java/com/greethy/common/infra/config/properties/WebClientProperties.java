package com.greethy.common.infra.config.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebClientProperties {
    private String name;
    private String baseUrl;
    private String authorization;
}
