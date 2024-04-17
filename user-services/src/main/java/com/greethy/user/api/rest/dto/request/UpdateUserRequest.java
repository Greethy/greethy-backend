package com.greethy.user.api.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greethy.user.core.domain.value.PersonalDetail;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateUserRequest {

    private String avatar;

    @JsonProperty("banner_image")
    private String bannerImage;

    private String bio;

    @JsonProperty("personal_info")
    private PersonalDetail personalDetail;

}
