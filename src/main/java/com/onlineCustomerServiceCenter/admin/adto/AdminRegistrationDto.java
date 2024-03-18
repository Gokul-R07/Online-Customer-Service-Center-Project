package com.onlineCustomerServiceCenter.admin.adto;

import lombok.Data; // Import the @Data annotation from Lombok

@Data // Add @Data annotation here
public class AdminRegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public AdminRegistrationDto(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public AdminRegistrationDto() {
    }

    public Object getName() {
        return null;
    }
}
