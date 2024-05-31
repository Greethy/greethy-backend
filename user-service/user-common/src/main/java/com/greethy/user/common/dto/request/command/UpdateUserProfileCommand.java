package com.greethy.user.common.dto.request.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greethy.user.common.dto.value.PersonalDetailDto;
import lombok.Data;

@Data
public class UpdateUserProfileCommand {

    private String userId;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    @JsonProperty("banner_url")
    private String bannerUrl;

    private String bio;

    @JsonProperty("personal_detail")
    private PersonalDetailDto personalDetail;

}
