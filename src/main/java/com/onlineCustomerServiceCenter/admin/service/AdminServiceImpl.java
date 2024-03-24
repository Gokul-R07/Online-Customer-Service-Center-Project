package com.onlineCustomerServiceCenter.admin.service;

import com.onlineCustomerServiceCenter.admin.dao.AdminRepository;
import com.onlineCustomerServiceCenter.admin.entity.Admin;
import com.onlineCustomerServiceCenter.admin.exceptions.AdminNotFoundException;
import com.onlineCustomerServiceCenter.admin.exceptions.NullException;
import com.onlineCustomerServiceCenter.admin.exceptions.OperatorNotFoundException;
import com.onlineCustomerServiceCenter.issue.dao.IssueRepository;
import com.onlineCustomerServiceCenter.issue.entity.Issue;
import com.onlineCustomerServiceCenter.issue.entity.IssueStatus;
import com.onlineCustomerServiceCenter.operator.dao.OperatorRepository;
import com.onlineCustomerServiceCenter.operator.entity.Operator;
import com.onlineCustomerServiceCenter.operator.exceptions.IncorrectPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private OperatorRepository operatorRepository;
    @Autowired
    private IssueRepository issueRepository;
    @Override
    public Admin registerAdmin(Admin newAdmin) throws NullException {
        if(newAdmin.getFirstName()==null){
            throw new NullException("First Name cannot be null");
        } else if (newAdmin.getLastName()==null) {
            throw new NullException("Last Name cannot be null");
        } else if (newAdmin.getEmail()==null) {
            throw new NullException("Email cannot be null");
        } else if (newAdmin.getPassword()==null) {
            throw new NullException("Password cannot be null");
        }

        return this.adminRepository.save(newAdmin);
    }

    @Override
    public Admin loginAdmin(String email, String password) throws NullException, IncorrectPasswordException ,AdminNotFoundException{

        if(email==null ){
            throw new NullException("Email cannot be null");
        } else if (password==null) {
            throw new NullException("Password cannot be null");
        }

        Optional<Admin> adminOptional= this.adminRepository.findAdminByEmail(email);

        if (adminOptional.isPresent()) {
           Admin admin = adminOptional.get();
            if (admin.getPassword().equals(password)) {
                return admin;
            } else {
                throw new IncorrectPasswordException("Incorrect password");
            }
        } else {
            throw new AdminNotFoundException("Admin not found");
        }

    }

    @Override
    public Operator registerOperator(Operator newOperator) throws NullException {

        if(newOperator.getFirstName()==null){
            throw new NullException("First Name cannot be null");
        } else if (newOperator.getLastName()==null) {
            throw new NullException("Last Name cannot be null");
        } else if (newOperator.getEmail()==null) {
            throw new NullException("Email cannot be null");
        } else if (newOperator.getPassword()==null) {
            throw new NullException("Password cannot be null");
        } else if (newOperator.getDepartmentName()==null) {
            throw new NullException("Department Name cannot be null");
        } else if (newOperator.getPhoneNumber()==null) {
            throw new NullException("Phone Number cannot be null");
        } else if (newOperator.getCity()==null) {
            throw new NullException("City cannot be null");
        }
        return this.operatorRepository.save(newOperator);
    }


    @Override
    public Operator updateOperatorDetails(Operator updatedOperator) throws NullException {
        if(updatedOperator.getFirstName()==null){
            throw new NullException("First Name cannot be null");
        } else if (updatedOperator.getLastName()==null) {
            throw new NullException("Last Name cannot be null");
        } else if (updatedOperator.getEmail()==null) {
            throw new NullException("Email cannot be null");
        } else if (updatedOperator.getPassword()==null) {
            throw new NullException("Password cannot be null");
        } else if (updatedOperator.getDepartmentName()==null) {
            throw new NullException("Department Name cannot be null");
        } else if (updatedOperator.getPhoneNumber()==null) {
            throw new NullException("Phone Number cannot be null");
        } else if (updatedOperator.getCity()==null) {
            throw new NullException("City cannot be null");
        }

        return this.operatorRepository.save(updatedOperator);
    }

    @Override
    public String deleteOperatorById(Integer operatorId) throws NullException, OperatorNotFoundException {
        if(operatorId==null){
            throw new NullException("Operator Id cannot be null");
        }
        Optional<Operator>operatorOptional=this.operatorRepository.findById(operatorId);
        if(operatorOptional.isEmpty()){
            throw new OperatorNotFoundException("Operator not found");
        }
        this.operatorRepository.deleteById(operatorId);
        return "Operator deleted successfully";
    }


    @Override
    public String allocateIssueToOperator(Issue newIssue) {
        List<Operator> operators = this.operatorRepository.findAll();

        operators.sort(Comparator.comparingLong(o -> o.getCustomerIssues().size()));
        operators.sort(Comparator.comparingLong(o -> o.getCustomerIssues()
                .stream()
                .filter(issue -> issue.getIssueStatus().equals(IssueStatus.INPROGRESS))
                .count()));

        Operator selectedOperator = operators.get(0);
        List<Issue> operatorIssues = selectedOperator.getCustomerIssues();

        operatorIssues.add(newIssue);
        selectedOperator.setCustomerIssues(operatorIssues);
        this.operatorRepository.save(selectedOperator);
        newIssue.setIssueStatus(IssueStatus.INPROGRESS);
        this.issueRepository.save(newIssue);


        return "New issue allocated to Operator with ID: " + selectedOperator.getOperatorId();
    }
}
