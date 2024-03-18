package com.onlineCustomerServiceCenter.admin.service;

import com.onlineCustomerServiceCenter.admin.dto.AdminLoginDto;
import com.onlineCustomerServiceCenter.admin.dto.AdminRegistrationDto;
import com.onlineCustomerServiceCenter.admin.entity.Admin;
import com.onlineCustomerServiceCenter.operator.entity.Operator;

public interface AdminService {

    Admin registerAdmin(Admin newAdmin);

    Admin loginAdmin(String email, String password);

    Operator registerOperator(Operator newOperator);
}
