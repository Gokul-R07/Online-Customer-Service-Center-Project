package com.onlineCustomerServiceCenter.admin.controller;

import com.onlineCustomerServiceCenter.admin.dto.AdminLoginDto;
import com.onlineCustomerServiceCenter.admin.entity.Admin;
import com.onlineCustomerServiceCenter.admin.exceptions.NullException;
import com.onlineCustomerServiceCenter.admin.exceptions.OperatorNotFoundException;
import com.onlineCustomerServiceCenter.admin.service.AdminService;
import com.onlineCustomerServiceCenter.issue.entity.Issue;
import com.onlineCustomerServiceCenter.operator.entity.Operator;
import com.onlineCustomerServiceCenter.operator.exceptions.IncorrectPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/admin/register")
    public Admin registerAdmin(@RequestBody Admin newAdmin) throws NullException {
        return this.adminService.registerAdmin(newAdmin);
    }

    @PostMapping("/admin/login")
    public Admin loginAdmin(@RequestBody AdminLoginDto adminLoginDto) throws IncorrectPasswordException, NullException {
        return this.adminService.loginAdmin(adminLoginDto.getEmail(), adminLoginDto.getPassword());
    }

    @PostMapping("/admin/register-operator")
    public Operator registerOperator(@RequestBody Operator newOperator) throws NullException {
        return this.adminService.registerOperator(newOperator);
    }

    @PutMapping("/admin/update-operator")
    public  Operator updateOperatorDetails(@RequestBody Operator updatedOperator) throws NullException {
        return this.adminService.updateOperatorDetails(updatedOperator);
    }

    @DeleteMapping("/admin/delete-operator")
    public String deleteOperator(@RequestParam Integer operatorId) throws OperatorNotFoundException, NullException {

        return  this.adminService.deleteOperatorById(operatorId);
    }


    @PostMapping("/admin/allocate-issue")
    public String allocateIssueToOperator(@RequestBody Issue neweIsuue) {
        return this.adminService.allocateIssueToOperator(neweIsuue);
    }
}
