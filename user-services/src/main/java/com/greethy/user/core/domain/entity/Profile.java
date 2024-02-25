package com.greethy.user.core.domain.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    private Avatar avatar;

    private String background;

    @Field(name = "first_name")
    private String firstname;

    @Field(name = "last_name")
    private String lastname;

    private LocalDate birthday;

    private Address address;

    private String bio;

    private List<Contact> contact;

    @Data
    static class Avatar {

        String url;

        final Integer width = 300;

        final Integer height = 300;

    }

}

