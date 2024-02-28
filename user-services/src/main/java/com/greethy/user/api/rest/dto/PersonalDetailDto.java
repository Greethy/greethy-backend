package com.greethy.user.api.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonalDetailDto {

    @JsonProperty("real_name")
    private String realName;

    private String birthday;

    private String location;

    private String website;

    private Integer gender;

}
