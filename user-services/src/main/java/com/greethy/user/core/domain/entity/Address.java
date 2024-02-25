package com.greethy.user.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents an address with country, city, district, and detail information.
 *
 * @author ThanhKien
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String country;

    private String city;

    private String district;

    private String detail;

}
