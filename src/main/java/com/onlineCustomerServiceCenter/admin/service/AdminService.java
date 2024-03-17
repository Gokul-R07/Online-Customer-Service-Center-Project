package com.onlineCustomerServiceCenter.admin.service;

import com.onlineCustomerServiceCenter.admin.dto.AdminLoginDto;
import com.onlineCustomerServiceCenter.admin.dto.AdminRegistrationDto;
import com.onlineCustomerServiceCenter.admin.entity.Admin;
import com.onlineCustomerServiceCenter.operator.entity.Operator;

public interface AdminService {

//    default Admin adminLogin(AdminLoginDto adminLoginDto) {
//        return null;
//    }
//
//    default Admin registerAdmin(AdminRegistrationDto adminRegistrationDto) {
//        return null;
//    }

    boolean deleteOperator(int operatorId);

    Operator createOperator(Operator operator);
}
