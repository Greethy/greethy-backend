package com.greethy.personal.dto;

import com.greethy.personal.entity.Address;
import com.greethy.personal.entity.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRequest {

    private String username;

    private String email;

    private String firstname;

    private String lastname;

    private LocalDate birthday;

    private Address address;

    private String avatarUrl;

    private String bannerUrl;

    private Contact contact;

}
