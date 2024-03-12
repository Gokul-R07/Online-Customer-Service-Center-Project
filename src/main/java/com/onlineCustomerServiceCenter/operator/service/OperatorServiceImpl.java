package com.onlineCustomerServiceCenter.operator.service;

import com.onlineCustomerServiceCenter.issue.Issue;
import com.onlineCustomerServiceCenter.issue.IssueRepository;
import com.onlineCustomerServiceCenter.issue.IssueService;
import com.onlineCustomerServiceCenter.issue.exception.IssueNotFoundException;
import com.onlineCustomerServiceCenter.operator.dao.OperatorRepository;
import com.onlineCustomerServiceCenter.operator.entity.Operator;
import com.onlineCustomerServiceCenter.operator.exceptions.IncorrectPasswordException;
import com.onlineCustomerServiceCenter.operator.exceptions.NullException;
import com.onlineCustomerServiceCenter.operator.exceptions.OperatorNotFoundException;
import com.onlineCustomerServiceCenter.solution.dao.SolutionRepository;
import com.onlineCustomerServiceCenter.solution.entity.Solution;
import com.onlineCustomerServiceCenter.solution.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    private OperatorRepository operatorRepository;
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private SolutionRepository solutionRepository;
    @Autowired
    private SolutionService solutionService;
    @Autowired
    private IssueService issueService;


    @Override
    public String loginOperator(String email, String password) throws OperatorNotFoundException, IncorrectPasswordException, NullException {
        if(email==null ){
            throw new NullException("Email cannot be null");
        } else if (password==null) {
            throw new NullException("Password cannot be null");
        }

        Optional<Operator> operatorOptional = operatorRepository.findOperatorByEmail(email);

        if (operatorOptional.isPresent()) {
            Operator operator = operatorOptional.get();
            if (operator.getPassword().equals(password)) {
                return "Login successful";
            } else {
                throw new IncorrectPasswordException("Incorrect password");
            }
        } else {
            throw new OperatorNotFoundException("Operator not found for given email id: "+email);
        }
    }

    @Override
    public String changePassword(String email, String oldPassword, String newPassword) throws OperatorNotFoundException, IncorrectPasswordException, NullException {
        if(email==null){
            throw new NullException("Operator Email cannot be null");
        }

        else if (oldPassword==null) {
            throw new NullException("Old password cannot be null");
        }
        else if (newPassword==null) {
            throw new NullException("New password cannot be null");
        }
        Optional<Operator> operatorOptional=this.operatorRepository.findOperatorByEmail(email);
        if(operatorOptional.isPresent()){
            Operator foundOperator=operatorOptional.get();
            if(!foundOperator.getPassword().equals(oldPassword)){
                throw new IncorrectPasswordException("Old password is wrong");
            }
            foundOperator.setPassword(newPassword);
            this.operatorRepository.save(foundOperator);
            return "Password changed successfully";
        }
        else{
            throw new OperatorNotFoundException("Operator not found for given email id: "+email);
        }

    }


    @Override
    public String addIssueSolution(Integer issueId, String solutionDescription,Integer operatorId) throws  IssueNotFoundException, NullException {
        if(issueId==null){
            throw new NullException("Issue Id cannot be null");
        } else if (solutionDescription==null) {
            throw new NullException("Solution description cannot be null");
        } else if (operatorId==null) {
            throw new NullException("Operator Id cannot be null");
        }

        Optional<Issue> issueOptional=  this.issueRepository.findById(issueId);
         if(issueOptional.isPresent()){
             Solution solution= new Solution(solutionDescription,operatorId);
             Solution savedSolution= this.solutionRepository.save(solution);
             Issue issue=issueOptional.get();
             issue.getSolutions().add(savedSolution);
             this.issueRepository.save(issue);
             return "Solution saved successfully";
         }else{

             throw new IssueNotFoundException("No Issue found with given issue id:"+ issueId);
         }



    }

}
