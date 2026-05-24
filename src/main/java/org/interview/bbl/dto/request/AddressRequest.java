package org.interview.bbl.dto.request;

import lombok.Data;

@Data
public class AddressRequest {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoRequest geo;
}

