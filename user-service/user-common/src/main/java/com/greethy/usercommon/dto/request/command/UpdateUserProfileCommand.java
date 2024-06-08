package com.greethy.usercommon.dto.request.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greethy.usercommon.dto.value.PersonalDetailDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateUserProfileCommand {

    @JsonProperty("avatar_url")
    @NotBlank(message = "")
    private String avatarUrl;

    @JsonProperty("banner_url")
    @NotBlank(message = "")
    private String bannerUrl;

    @NotBlank(message = "")
    private String bio;

    @JsonProperty("personal_detail")
    private PersonalDetailDto personalDetail;

}
