package com.greethy.user.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
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

    private Gender gender;

    @Getter
    @AllArgsConstructor
    public enum Gender{

        FEMALE(0, "FEMALE"),
        MALE(1, "MALE"),
        OTHER(2, "OTHER");

        private final int type;
        private final String name;
    }
}

