package com.onlineCustomerServiceCenter.admin.service;

import com.onlineCustomerServiceCenter.admin.entity.Admin;
import com.onlineCustomerServiceCenter.admin.dao.AdminRepository;
import com.onlineCustomerServiceCenter.operator.dao.OperatorRepository;
import com.onlineCustomerServiceCenter.operator.entity.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private OperatorRepository operatorRepository;

    @Override
    public Admin registerAdmin(Admin newAdmin) {
        return adminRepository.save(newAdmin);
    }

    @Override
    public Admin loginAdmin(String email, String password) {
        return adminRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public Operator registerOperator(Operator newOperator) {
        return this.operatorRepository.save(newOperator);
    }
}

