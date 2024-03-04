package com.onlineCustomerServiceCenter.operator.service;

import com.onlineCustomerServiceCenter.issue.Issue;
import com.onlineCustomerServiceCenter.issue.IssueRepository;
import com.onlineCustomerServiceCenter.issue.IssueService;
import com.onlineCustomerServiceCenter.issue.exception.IssueNotFoundException;
import com.onlineCustomerServiceCenter.issue.exception.NullIssueException;
import com.onlineCustomerServiceCenter.operator.dao.OperatorRespository;
import com.onlineCustomerServiceCenter.operator.dto.OperatorLoginDto;
import com.onlineCustomerServiceCenter.operator.entity.Operator;
import com.onlineCustomerServiceCenter.operator.exceptions.IncorrectPasswordException;
import com.onlineCustomerServiceCenter.operator.exceptions.NullException;
import com.onlineCustomerServiceCenter.operator.exceptions.OperatorNotFoundException;
import com.onlineCustomerServiceCenter.solution.dao.SolutionRepository;
import com.onlineCustomerServiceCenter.solution.exceptions.SolutionException;
import com.onlineCustomerServiceCenter.solution.entity.Solution;
import com.onlineCustomerServiceCenter.solution.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    private OperatorRespository operatorRespository;
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

        Optional<OperatorLoginDto> foundOperator= this.operatorRespository.findOperatorByEmail(email);
        if(foundOperator.isPresent()){
                if(foundOperator.get().getPassword().equals(password)){
                    return "Login Success";
                }else{
                    throw new IncorrectPasswordException( "Password is Wrong");
                }

        }else {
            throw  new OperatorNotFoundException("Operator does not exits for given email:"+email);
        }
    }

    @Override
    public String changePassword(Integer operatorId, String oldPassword, String newPassword) throws OperatorNotFoundException, IncorrectPasswordException, NullException {
        if(operatorId==null){
            throw new NullException("Operator Id cannot be null");
        }

        else if (oldPassword==null) {
            throw new NullException("Old password cannot be null");
        }
        else if (newPassword==null) {
            throw new NullException("New password cannot be null");
        }
        Optional<Operator> operatorOptional=this.operatorRespository.findById(operatorId);
        if(operatorOptional.isPresent()){
            Operator foundOperator=operatorOptional.get();
            if(!foundOperator.getPassword().equals(oldPassword)){
                throw new IncorrectPasswordException("Old password is wrong");
            }
            foundOperator.setPassword(newPassword);
            this.operatorRespository.save(foundOperator);
            return "Password changed successfully";
        }
        else{
            throw new OperatorNotFoundException("Given Operator Id does not exists:"+operatorId);
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
