package com.greethy.user.api.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greethy.user.core.domain.entity.Network;
import com.greethy.user.core.domain.entity.Premium;
import com.greethy.user.core.domain.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {

    private String id;

    private String username;

    private String email;

    private Boolean verified;

    private String avatar;

    @JsonProperty("banner_image")
    private String bannerImage;

    private String bio;

    @JsonProperty("personal_info")
    private PersonalDetailDto personalDetail;

    private Premium premium;

    private Network network;

    private List<Role> roles;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
