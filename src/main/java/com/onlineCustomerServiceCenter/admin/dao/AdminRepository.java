package com.onlineCustomerServiceCenter.admin.dao;

import com.onlineCustomerServiceCenter.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByEmailAndPassword(String email, String password);
}
