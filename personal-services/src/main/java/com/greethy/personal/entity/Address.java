package com.greethy.personal.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents an address with country, city, district, and detail information.
 *
 * @author ThanhKien
 */
@Data
@NoArgsConstructor
public class Address {

    private String country;

    private String city;

    private String district;

    private String detail;

}
