package com.greethy.user.core.domain.entity;

import lombok.*;
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

    private Integer gender;

}