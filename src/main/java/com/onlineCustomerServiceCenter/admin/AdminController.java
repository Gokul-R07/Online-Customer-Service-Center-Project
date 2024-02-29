package com.onlineCustomerServiceCenter.admin;

import com.onlineCustomerServiceCenter.operator.entity.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;
    @PostMapping("operator")
    public Operator createOperator(@RequestBody Operator newOperator){
       return this.adminService.createOperator(newOperator);
    }

}
