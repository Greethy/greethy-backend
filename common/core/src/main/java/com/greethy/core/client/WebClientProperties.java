package com.greethy.core.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebClientProperties {
    private String name;
    private String address;
    private String username;
    private String password;
    private String authorization;
    private List<ExchangeFilterFunction> customFilters;
    private boolean internalOauth = false;

}
