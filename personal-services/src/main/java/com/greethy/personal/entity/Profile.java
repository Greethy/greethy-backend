package com.greethy.personal.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

/**
 * @author ThanhKien
 * */
@Data
public class Profile {

    @CreatedDate
    @Field(name = "created_date")
    private LocalDate createdDate;

    @LastModifiedDate
    @Field(name = "updated_date")
    private LocalDate updatedDate;

    @Field(name = "first_name")
    private String firstname;

    @Field(name = "last_name")
    private String lastname;

    @Field(name = "avatar_url")
    private String avatarUrl;

    private Address address;

    private LocalDate birthday;
}
