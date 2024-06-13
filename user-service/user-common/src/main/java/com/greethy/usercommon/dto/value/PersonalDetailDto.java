package com.greethy.usercommon.dto.value;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonalDetailDto {

    @JsonProperty("real_name")
    private String realName;

    private LocalDate birthday;

    private String location;

    private String website;

    private String gender;

}
