package com.onlineCustomerServiceCenter.admin.adto;

import lombok.Data; // Import the @Data annotation from Lombok

@Data // Add @Data annotation here
public class AdminRegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
