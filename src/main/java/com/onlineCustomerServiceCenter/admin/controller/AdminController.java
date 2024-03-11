package com.onlineCustomerServiceCenter.admin.controller;

import com.onlineCustomerServiceCenter.admin.adto.AdminLoginDto;
import com.onlineCustomerServiceCenter.admin.adto.AdminRegistrationDto;
import com.onlineCustomerServiceCenter.admin.entity.Admin;
import com.onlineCustomerServiceCenter.admin.service.AdminService;
import com.onlineCustomerServiceCenter.operator.entity.Operator;
import com.onlineCustomerServiceCenter.operator.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Admin login endpoint
    @PostMapping("/login")
    public ResponseEntity<String> adminLogin(@RequestBody AdminLoginDto adminLoginDto) {
        String loginResult = OperatorService.loginOperator(adminLoginDto.getEmail(), adminLoginDto.getPassword());
        return new ResponseEntity<>(loginResult, HttpStatus.OK);
    }

    // Register a new admin endpoint
    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@RequestBody AdminRegistrationDto adminRegistrationDto) {
        Admin registrationSuccess = adminService.registerAdmin(adminRegistrationDto);

        ResponseEntity<String> stringResponseEntity;
        // Assuming this is how you define registration success
        boolean registrationSuccess = true;
        if (registrationSuccess) {
            stringResponseEntity = new ResponseEntity<>("Admin registration successful", HttpStatus.CREATED);
        } else {
            stringResponseEntity = new ResponseEntity<>("Admin registration failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        ResponseEntity<String> response = stringResponseEntity;
        return response;


    }

    // Add a new operator endpoint
    @PostMapping("/operators/add")
    public ResponseEntity<Operator> addOperator(@RequestBody Operator operator) {
        Operator addedOperator = OperatorService.updateOperatorProfile(operator);
        return new ResponseEntity<>(addedOperator, HttpStatus.CREATED);
    }

    // Update an existing operator endpoint
    @PutMapping("/operators/update")
    public ResponseEntity<Operator> updateOperator(@RequestBody Operator operator) {
        Operator updatedOperator = OperatorService.updateOperatorProfile(operator);
        return new ResponseEntity<>(updatedOperator, HttpStatus.OK);
    }

    // Delete an operator by operator ID endpoint
    @DeleteMapping("/operators/delete/{optId}")
    public ResponseEntity<Void> deleteOperator(@PathVariable Integer optId) {
        boolean deletionSuccessful = OperatorService.deleteOperator(optId);
        if (deletionSuccessful) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // or HttpStatus.INTERNAL_SERVER_ERROR based on the scenario
        }
    }
}




