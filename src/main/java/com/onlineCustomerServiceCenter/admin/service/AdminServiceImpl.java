package com.onlineCustomerServiceCenter.admin.service;

import com.onlineCustomerServiceCenter.admin.dto.AdminLoginDto;
import com.onlineCustomerServiceCenter.admin.dto.AdminRegistrationDto;
import com.onlineCustomerServiceCenter.admin.entity.Admin;
import com.onlineCustomerServiceCenter.operator.entity.Operator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final List<Admin> adminList = new ArrayList<>();

    @Override
    public Admin adminLogin(AdminLoginDto adminLoginDto) {
        // Simulated logic to validate admin login
        for (Admin admin : adminList) {
            if (admin.getPassword().equals(adminLoginDto.getPassword()))
                if (admin.getEmail().equals(adminLoginDto.getEmail())) {
                    return admin; // Return the logged-in admin
                }
        }
        return null; // Return null if login credentials are invalid
    }

    @Override
    public Admin registerAdmin(AdminRegistrationDto adminRegistrationDto) {
        // Simulated logic to register a new admin
        Admin newAdmin = new Admin(adminRegistrationDto.getFirstName(), adminRegistrationDto.getLastName(),
                adminRegistrationDto.getEmail(), adminRegistrationDto.getPassword());
        adminList.add(newAdmin); // Add the new admin to the list
        return newAdmin; // Return the registered admin
    }

    @Override
    public boolean deleteOperator(int operatorId) {
        return false;
    }

    @Override
    public Operator createOperator(Operator operator) {
        return null;
    }
}
