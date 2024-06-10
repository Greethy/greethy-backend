package com.greethy.usercommon.entity.value;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDetail {

    @Field(name = "real_name")
    private String realName;

    private LocalDate birthday;

    private String location;

    private String website;

    private String gender;
}
