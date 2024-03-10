package com.onlineCustomerServiceCenter.admin.controller;

import com.onlineCustomerServiceCenter.admin.adto.AdminLoginDto;
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
    public OperatorService operatorService;

    // Admin login endpoint
    @PostMapping("/login")
    public ResponseEntity<String> adminLogin(@RequestBody AdminLoginDto adminLoginDto) {
        String loginResult = operatorService.loginOperator(adminLoginDto.getEmail(), adminLoginDto.getPassword());
        return new ResponseEntity<>(loginResult, HttpStatus.OK);
    }



    // Register a new admin endpoint


    // Add a new operator endpoint
    @PostMapping("/operators/add")
    public ResponseEntity<Operator> addOperator(@RequestBody Operator operator) {
        Operator addedOperator = operatorService.updateOperatorProfile(operator);
        return new ResponseEntity<>(addedOperator, HttpStatus.CREATED);
    }

    // Update an existing operator endpoint
    @PutMapping("/operators/update")
    public ResponseEntity<Operator> updateOperator(@RequestBody Operator operator) {
        Operator updatedOperator = operatorService.updateOperatorProfile(operator);
        return new ResponseEntity<>(updatedOperator, HttpStatus.OK);
    }

    // Delete an operator by operator ID endpoint
    // Delete an operator by operator ID endpoint
    @DeleteMapping("/operators/delete/{optId}")
    public ResponseEntity<Void> deleteOperator(@PathVariable Integer optId) {
        // Implement delete operator logic using OperatorService
        boolean deletionSuccessful = operatorService.deleteOperator(optId);
        if (deletionSuccessful) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // or HttpStatus.INTERNAL_SERVER_ERROR based on the scenario
        }
    }
}





