package com.onlineCustomerServiceCenter.admin.controller;

import com.onlineCustomerServiceCenter.admin.model.Admin;
import com.onlineCustomerServiceCenter.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/registerAdmin")
    public Admin registerAdmin(@RequestBody Admin newAdmin) {
        return adminService.registerAdmin(newAdmin);
    }

    @PostMapping("/loginAdmin")
    public Admin loginAdmin(@RequestBody Admin adminCredentials) {
        return adminService.loginAdmin(adminCredentials.getEmail(), adminCredentials.getPassword());
    }
}