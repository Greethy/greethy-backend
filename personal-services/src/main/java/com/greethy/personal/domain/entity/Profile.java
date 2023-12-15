package com.greethy.personal.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Field(name = "first_name")
    private String firstname;

    @Field(name = "last_name")
    private String lastname;

    private LocalDate birthday;

    private Address address;

    private List<Contact> contact;

    @Field(name = "created_date")
    private LocalDate createdDate;

    @Field(name = "updated_date")
    private LocalDate updatedDate;

}