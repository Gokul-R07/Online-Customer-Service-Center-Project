package com.onlineCustomerServiceCenter.admin.service;

import com.onlineCustomerServiceCenter.admin.adto.AdminLoginDto;
import com.onlineCustomerServiceCenter.admin.adto.AdminRegistrationDto;
import com.onlineCustomerServiceCenter.admin.entity.Admin;

public interface AdminService {

    default Admin adminLogin(AdminLoginDto adminLoginDto) {
        return null;
    }

    default Admin registerAdmin(AdminRegistrationDto adminRegistrationDto) {
        return null;
    }
}
