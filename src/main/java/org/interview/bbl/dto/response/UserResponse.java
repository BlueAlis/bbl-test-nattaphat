package org.interview.bbl.dto.response;

import lombok.Data;
import org.interview.bbl.model.enums.UserStatus;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;
    private UserStatus status;
    private AddressResponse address;
    private CompanyResponse company;
}

