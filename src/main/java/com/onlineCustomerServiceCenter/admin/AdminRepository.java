package com.onlineCustomerServiceCenter.admin.repository;

import com.onlineCustomerServiceCenter.admin.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Admin findByEmailAndPassword(String email, String password);
}
