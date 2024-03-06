package com.onlineCustomerServiceCenter.operator.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data

public class PasswordDto {

    String email;
    String oldPassword;
    String newPassword;



}
