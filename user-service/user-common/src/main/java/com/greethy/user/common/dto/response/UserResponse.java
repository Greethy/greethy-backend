package com.greethy.user.common.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greethy.user.common.dto.value.NetworkingDto;
import com.greethy.user.common.dto.value.PremiumDto;
import com.greethy.user.common.dto.value.RoleDto;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse {

    private String id;

    private String username;

    private String email;

    private String password;

    private Boolean verified;

    private String avatar;

    @JsonProperty("banner_url")
    private String bannerUrl;

    private String bio;

    private PremiumDto premium;

    private NetworkingDto networking;

    private List<RoleDto> roles;

    @JsonProperty("body_specs_ids")
    private List<String> bodySpecsIds;

}
