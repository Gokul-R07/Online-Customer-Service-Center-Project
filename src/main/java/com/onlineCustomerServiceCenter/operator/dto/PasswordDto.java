package com.onlineCustomerServiceCenter.operator.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data

public class PasswordDto {

    Integer operatorId;
    String oldPassword;
    String newPassword;

    public PasswordDto(Integer operatorId, String oldPassword, String newPassword) {
        this.operatorId = operatorId;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public PasswordDto() {
    }

}
