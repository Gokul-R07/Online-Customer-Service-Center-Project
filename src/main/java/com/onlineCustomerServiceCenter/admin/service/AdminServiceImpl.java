package com.onlineCustomerServiceCenter.admin.service;

import com.onlineCustomerServiceCenter.admin.adto.AdminLoginDto;
import com.onlineCustomerServiceCenter.admin.adto.AdminRegistrationDto;
import com.onlineCustomerServiceCenter.admin.dao.AdminRepository;
import com.onlineCustomerServiceCenter.admin.entity.Admin;
import com.onlineCustomerServiceCenter.admin.exceptions.AdminLoginException;
import com.onlineCustomerServiceCenter.admin.exceptions.AdminRegistrationException;
import com.onlineCustomerServiceCenter.operator.dao.OperatorRepository;
import com.onlineCustomerServiceCenter.operator.entity.Operator;
import com.onlineCustomerServiceCenter.operator.exceptions.OperatorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {


    private AdminRepository adminRepository;

    @Autowired
    private OperatorRepository operatorRepository;

    @Override
    public Admin adminLogin(AdminLoginDto adminLoginDto)  {
        String email = adminLoginDto.getEmail();
        String password = adminLoginDto.getPassword();

        Admin admin = adminRepository.findByEmailAndPassword(email, password);
        if (admin == null) {
            throw new AdminLoginException("Invalid credentials. Please check your email and password.");
        }

        return admin;
    }

    @Override
    public Admin registerAdmin(AdminRegistrationDto adminRegistrationDto) {
        String email = adminRegistrationDto.getEmail();
        String password = adminRegistrationDto.getPassword();

        Admin existingAdmin = adminRepository.findByEmail(email);
        if (existingAdmin != null) {
            throw new AdminRegistrationException("Admin with email " + email + " already exists.");
        }

        Admin newAdmin = new Admin();
        return adminRepository.save(newAdmin);
    }


    @Override
    public Operator deleteOperator(int operatorId) {

            Optional<Operator> operatorOpt = this.operatorRepository.findById(operatorId);
            this.operatorRepository.deleteById(operatorOpt.get().getOperatorId());
            Operator operatorToBeDeleted= operatorOpt.get();
            return  operatorToBeDeleted;
        }



    @Override
    public Operator createOperator(Operator operator) {
        // Implement logic to create a new Operator
        return operatorRepository.save(operator);
    }

    @Override
    public Operator updateOperator(Operator operator) {
        Optional<Operator> existingOperator = null;
        Operator updatedOperator = new Operator();
        try {
            existingOperator = operatorRepository.findById(operator.getOperatorId());
            if (existingOperator.isEmpty()) {
                throw new OperatorNotFoundException("Operator not found");
            }
            updatedOperator.setFirstName(operator.getName());
            updatedOperator.setEmail(operator.getEmail());
            updatedOperator.setPhoneNumber(operator.getPhoneNumber());
            operatorRepository.save(updatedOperator);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return updatedOperator;
    }
}
