package com.greethy.personal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Field(name = "created_date")
    private LocalDate createdDate;

    @Field(name = "updated_date")
    private LocalDate updatedDate;

    private String username;

    private String email;

    @Field(name = "first_name")
    private String firstname;

    @Field(name = "last_name")
    private String lastname;

    private LocalDate birthday;

    private Address address;

    @Field(name = "avatar_url")
    private String avatarUrl;

    private String bannerUrl;

    private Contact contact;

}
