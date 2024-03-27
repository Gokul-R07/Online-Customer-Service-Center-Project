package com.onlineCustomerServiceCenter.operator.dto;

import lombok.Data;

@Data

public class PasswordDto {

    Integer operatorId;
    String oldPassword;
    String newPassword;



}
