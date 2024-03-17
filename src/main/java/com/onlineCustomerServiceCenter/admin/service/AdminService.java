package com.onlineCustomerServiceCenter.admin.service;

import com.onlineCustomerServiceCenter.admin.entity.Admin;

public interface AdminService {

    Admin registerAdmin(Admin newAdmin);

    Admin loginAdmin(String email, String password);
}
