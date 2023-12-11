package com.greethy.personal.application.rest.request;

import com.greethy.personal.domain.entity.Address;
import com.greethy.personal.domain.entity.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRequest {

    private String firstname;

    private String lastname;

    private String birthday;

    private Address address;

    private Contact contact;

}
