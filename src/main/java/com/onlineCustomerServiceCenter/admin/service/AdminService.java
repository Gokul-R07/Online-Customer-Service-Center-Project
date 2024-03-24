package com.onlineCustomerServiceCenter.admin.service;

import com.onlineCustomerServiceCenter.admin.entity.Admin;
import com.onlineCustomerServiceCenter.admin.exceptions.AdminNotFoundException;
import com.onlineCustomerServiceCenter.admin.exceptions.NullException;
import com.onlineCustomerServiceCenter.admin.exceptions.OperatorNotFoundException;
import com.onlineCustomerServiceCenter.issue.entity.Issue;
import com.onlineCustomerServiceCenter.operator.entity.Operator;
import com.onlineCustomerServiceCenter.operator.exceptions.IncorrectPasswordException;

public interface AdminService {

    Admin registerAdmin(Admin newAdmin) throws NullException;

    Admin loginAdmin(String email, String password) throws NullException, IncorrectPasswordException, AdminNotFoundException;

    Operator registerOperator(Operator newOperator) throws NullException;

    String allocateIssueToOperator(Issue neweIsuue);

    Operator updateOperatorDetails(Operator updatedOperator) throws NullException;

    String deleteOperatorById(Integer operatorId) throws NullException, OperatorNotFoundException;
}
