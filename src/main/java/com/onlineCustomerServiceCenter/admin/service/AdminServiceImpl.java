package com.onlineCustomerServiceCenter.admin.service;

import com.onlineCustomerServiceCenter.admin.dao.AdminRepository;
import com.onlineCustomerServiceCenter.admin.dto.AdminLoginDto;
import com.onlineCustomerServiceCenter.admin.dto.AdminRegistrationDto;
import com.onlineCustomerServiceCenter.admin.entity.Admin;
import com.onlineCustomerServiceCenter.issue.dao.IssueRepository;
import com.onlineCustomerServiceCenter.issue.entity.Issue;
import com.onlineCustomerServiceCenter.issue.entity.IssueStatus;
import com.onlineCustomerServiceCenter.operator.dao.OperatorRepository;
import com.onlineCustomerServiceCenter.operator.entity.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private OperatorRepository operatorRepository;
    @Autowired
    private IssueRepository issueRepository;
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

    @Override
    public String allocateIssueToOperator(Issue newIssue) {
        List<Operator> operators = this.operatorRepository.findAll();

        // Sort operators based on allocated issue count and then pending issue count
        operators.sort(Comparator.comparingLong(o -> o.getCustomerIssues().size()));
        operators.sort(Comparator.comparingLong(o -> o.getCustomerIssues()
                .stream()
                .filter(issue -> issue.getIssueStatus().equals(IssueStatus.PENDING))
                .count()));

        Operator selectedOperator = operators.get(0); // Get the operator with the least allocated and pending issues
        List<Issue> operatorIssues = selectedOperator.getCustomerIssues();

        // Allocate the new issue to the selected operator
        operatorIssues.add(newIssue);
        selectedOperator.setCustomerIssues(operatorIssues);
        this.operatorRepository.save(selectedOperator);
        newIssue.setIssueStatus(IssueStatus.INPROGRESS);
        this.issueRepository.save(newIssue);


        return "New issue allocated to Operator with ID: " + selectedOperator.getOperatorId();
    }

}
