package com.greethy.gateway.api.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CurrentUserResponse {

    private String username;

    private String email;

    private Boolean verified;

    private String avatar;

    @JsonProperty("banner_image")
    private String bannerImage;

    private String bio;

    private List<String> roles;

}
