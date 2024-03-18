package com.onlineCustomerServiceCenter.admin.adto;

import lombok.Data; // Import the @Data annotation from Lombok

@Data // Add @Data annotation here
public class AdminLoginDto {
    private String email;
    private String password;


}
