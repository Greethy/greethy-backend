package com.greethy.usercommon.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greethy.usercommon.dto.value.NetworkingDto;
import com.greethy.usercommon.dto.value.PersonalDetailDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserProfileResponse {

    private String username;

    private String email;

    private Boolean verified;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    @JsonProperty("banner_url")
    @Schema(description = "URL to the user's banner image")
    private String bannerUrl;

    private String bio;

    @JsonProperty("personal_detail")
    private PersonalDetailDto personalDetail;

    private NetworkingDto networking;

}
