package com.greethy.usercommon.dto.request.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greethy.usercommon.dto.value.PersonalDetailDto;
import lombok.Data;

@Data
public class UpdateUserProfileCommand {

    @JsonProperty("avatar_url")
    private String avatarUrl;

    @JsonProperty("banner_url")
    private String bannerUrl;

    private String bio;

    @JsonProperty("personal_detail")
    private PersonalDetailDto personalDetail;

}
