package com.onlineCustomerServiceCenter.admin.service;

import com.onlineCustomerServiceCenter.admin.adto.AdminLoginDto;
import com.onlineCustomerServiceCenter.admin.adto.AdminRegistrationDto;
import com.onlineCustomerServiceCenter.admin.entity.Admin;
import com.onlineCustomerServiceCenter.operator.entity.Operator;

public interface AdminService {

    Admin adminLogin(AdminLoginDto adminLoginDto);

    Admin registerAdmin(AdminRegistrationDto adminRegistrationDto) ;
    

    boolean deleteOperator(int operatorId);

    Operator createOperator(Operator operator);


    Admin adminLogin(String email, String password);

    Admin registerAdmin(Admin newAdmin);
}
