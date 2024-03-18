package com.onlineCustomerServiceCenter.admin.controller;

import com.onlineCustomerServiceCenter.admin.adto.AdminLoginDto;
import com.onlineCustomerServiceCenter.admin.adto.AdminRegistrationDto;
import com.onlineCustomerServiceCenter.admin.entity.Admin;
import com.onlineCustomerServiceCenter.admin.repository.AdminRepository;
import com.onlineCustomerServiceCenter.operator.entity.Operator;
import com.onlineCustomerServiceCenter.operator.dao.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private OperatorRepository operatorRepository;

    @PostMapping("/login")
    public ResponseEntity<String> adminLogin(@RequestBody AdminLoginDto adminLoginDto) {
        com.onlineCustomerServiceCenter.admin.model.Admin admin = adminRepository.findByEmailAndPassword(adminLoginDto.getEmail(), adminLoginDto.getPassword());
        if (admin != null) {
            return new ResponseEntity<>("Admin logged in successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@RequestBody AdminRegistrationDto adminRegistrationDto) {
        Admin newAdmin = new Admin(adminRegistrationDto.getName(), adminRegistrationDto.getEmail(), adminRegistrationDto.getPassword());
        adminRepository.save(newAdmin);
        return new ResponseEntity<>("Admin registration successful", HttpStatus.CREATED);
    }

    @PostMapping("/operators/add")
    public ResponseEntity<Operator> addOperator(@RequestBody Operator operator) {
        Operator newOperator = operatorRepository.save(operator);
        return new ResponseEntity<>(newOperator, HttpStatus.CREATED);
    }

    @PutMapping("/operators/update")
    public ResponseEntity<Operator> updateOperator(@RequestBody Operator operator) {
        Operator updatedOperator = operatorRepository.save(operator);
        return new ResponseEntity<>(updatedOperator, HttpStatus.OK);
    }

    @DeleteMapping("/operators/delete/{optId}")
    public ResponseEntity<Void> deleteOperator(@PathVariable Long optId) {
        operatorRepository.deleteById(Math.toIntExact(optId));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}





