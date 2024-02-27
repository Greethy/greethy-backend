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

    /**
     * {@link Contact} class represents a contact entry, containing information such as label, icon, and contact details.
     *
     * @author ThanhKien
     */
    @Data
    static class Contact {

        private String label;

        private String icon;

        private String information;

    }

    /**
     * {@link Address} class represents an address with country, city, district, and detail information.
     *
     * @author ThanhKien
     */
    @Data
    static class Address {

        private String country;

        private String city;

        private String district;

        private String detail;

    }

}

