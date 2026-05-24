package org.interview.bbl.dto.request;

import lombok.Data;
import org.interview.bbl.model.enums.UserStatus;

@Data
public class UserRequest {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;
    private UserStatus status;
    private AddressRequest address;
    private CompanyRequest company;
}

