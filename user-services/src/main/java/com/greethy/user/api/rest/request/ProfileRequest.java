package com.greethy.user.api.rest.request;

import com.greethy.user.core.domain.entity.Address;
import com.greethy.user.core.domain.entity.Contact;
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
