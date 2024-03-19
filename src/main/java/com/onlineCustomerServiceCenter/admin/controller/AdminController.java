package com.onlineCustomerServiceCenter.admin.controller;

import com.onlineCustomerServiceCenter.admin.adto.AdminLoginDto;
import com.onlineCustomerServiceCenter.admin.adto.AdminRegistrationDto;
import com.onlineCustomerServiceCenter.admin.entity.Admin;
import com.onlineCustomerServiceCenter.admin.service.AdminService;
import com.onlineCustomerServiceCenter.operator.entity.Operator;
import com.onlineCustomerServiceCenter.operator.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {
    @Autowired
    private  AdminService adminService;
    @Autowired
    private  OperatorService operatorService;

    public AdminController(AdminService adminService, OperatorService operatorService) {
        this.adminService = adminService;
        this.operatorService = operatorService;
    }

    @PostMapping("admin/login")
    public Admin adminLogin(@RequestBody AdminLoginDto loginDto)  {
        return adminService.adminLogin(loginDto);
    }

    @PostMapping("admin/register")
    public Admin registerAdmin(@RequestBody AdminRegistrationDto newAdmin) {
        return adminService.registerAdmin(newAdmin);
    }


    @PostMapping("operator/add")
    public Operator addOperator(@RequestBody Operator newOperator)  {
        return operatorService.addOperator(newOperator);
    }

    @PutMapping("operator/update")
    public Operator updateOperator(@RequestBody Operator operator) {
        return adminService.updateOperator(operator);
    }

    @DeleteMapping("operator/{id}")
    public Operator deleteOperatorById(@PathVariable int id)  {
        return adminService.deleteOperator(id);
    }
}
