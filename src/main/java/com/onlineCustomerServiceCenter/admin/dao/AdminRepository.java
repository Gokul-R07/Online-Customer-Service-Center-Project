package com.onlineCustomerServiceCenter.admin.dao;

import com.onlineCustomerServiceCenter.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Optional< Admin> findAdminByEmail(String email);
}