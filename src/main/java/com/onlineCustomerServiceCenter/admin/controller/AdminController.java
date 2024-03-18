package com.onlineCustomerServiceCenter.admin.controller;

import com.onlineCustomerServiceCenter.admin.adto.AdminLoginDto;
import com.onlineCustomerServiceCenter.admin.entity.Admin;
import com.onlineCustomerServiceCenter.admin.service.AdminService;
import com.onlineCustomerServiceCenter.operator.entity.Operator;
import com.onlineCustomerServiceCenter.operator.service.OperatorService;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    private final AdminService adminService;
    private final OperatorService operatorService;

    public AdminController(AdminService adminService, OperatorService operatorService) {
        this.adminService = adminService;
        this.operatorService = operatorService;
    }

    @PostMapping("admin/login")
    public Admin adminLogin(@RequestBody AdminLoginDto loginDto)  {
        return adminService.adminLogin(loginDto.getEmail(), loginDto.getPassword());
    }

    @PostMapping("admin/register")
    public Admin registerAdmin(@RequestBody Admin newAdmin) {
        return adminService.registerAdmin(newAdmin);
    }


    @PostMapping("operator/add")
    public Operator addOperator(@RequestBody Operator newOperator)  {
        return operatorService.addOperator(newOperator);
    }

    @PutMapping("operator/update")
    public Operator updateOperator(@RequestBody Operator operator) {
        return operatorService.updateOperator(operator);
    }

    @DeleteMapping("operator/{id}")
    public Operator deleteOperatorById(@PathVariable Long id)  {
        return operatorService.deleteOperatorById(id);
    }
}

