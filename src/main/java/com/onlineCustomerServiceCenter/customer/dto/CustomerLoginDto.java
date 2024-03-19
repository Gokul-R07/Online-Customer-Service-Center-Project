package com.onlineCustomerServiceCenter.customer.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Setter
@Getter
@NoArgsConstructor
public class CustomerLoginDto {
    private String email;
    private String password;
}




