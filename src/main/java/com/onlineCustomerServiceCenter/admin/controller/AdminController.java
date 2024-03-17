package com.onlineCustomerServiceCenter.admin.controller;

import com.onlineCustomerServiceCenter.admin.entity.Admin;
import com.onlineCustomerServiceCenter.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AdminController {

    @Autowired
   private AdminService adminService;

//    @PostMapping("/registerAdmin")
//    public Admin registerAdmin(@RequestBody Admin newAdmin) {
//        return adminService.registerAdmin(newAdmin);
//    }
//
//    @PostMapping("/loginAdmin")
//    public Admin loginAdmin(@RequestBody Admin adminCredentials) {
//        return adminService.adminLogin(adminCredentials.getEmail(), adminCredentials.getPassword());
//    }
}
