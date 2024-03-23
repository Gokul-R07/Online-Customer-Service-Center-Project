package com.onlineCustomerServiceCenter.admin.controller;

import com.onlineCustomerServiceCenter.admin.entity.Admin;
import com.onlineCustomerServiceCenter.admin.service.AdminService;
import com.onlineCustomerServiceCenter.issue.entity.Issue;
import com.onlineCustomerServiceCenter.operator.entity.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
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
    @PostMapping("/operator/register")
    public Operator registerOperator(@RequestBody Operator newOperator) {
        return adminService.registerOperator(newOperator);
    }
    @PostMapping("/allocateIssue")
    public String allocateIssueToOperator(@RequestBody Issue neweIsuue) {
        return adminService.allocateIssueToOperator(neweIsuue);
    }
}
