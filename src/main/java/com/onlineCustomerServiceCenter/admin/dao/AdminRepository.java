package com.onlineCustomerServiceCenter.admin.dao;

import com.onlineCustomerServiceCenter.admin.entity.Admin;

public interface AdminRepository {

    Admin findByEmailAndPassword(String email, String password);

    Admin save(Admin newAdmin);

    Admin findByEmail(String email);
}
