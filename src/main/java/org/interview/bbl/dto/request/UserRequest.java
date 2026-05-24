package org.interview.bbl.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.interview.bbl.model.enums.UserStatus;

@Data
public class UserRequest {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid email address")
    private String email;

    private String phone;
    private String website;
    private UserStatus status;
    private AddressRequest address;
    private CompanyRequest company;
}

