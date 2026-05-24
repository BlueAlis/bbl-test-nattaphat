package org.interview.bbl.dto.response;

import lombok.Data;

@Data
public class AddressResponse {
    private Long id;
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoResponse geo;
}

