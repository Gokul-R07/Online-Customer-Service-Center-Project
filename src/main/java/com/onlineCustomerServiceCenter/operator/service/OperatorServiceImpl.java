package com.onlineCustomerServiceCenter.operator.service;

import com.onlineCustomerServiceCenter.issue.entity.Issue;
import com.onlineCustomerServiceCenter.issue.dao.IssueRepository;
import com.onlineCustomerServiceCenter.issue.service.IssueService;
import com.onlineCustomerServiceCenter.issue.exception.IssueNotFoundException;
import com.onlineCustomerServiceCenter.operator.dao.OperatorRespository;
import com.onlineCustomerServiceCenter.operator.dto.OperatorLoginDto;
import com.onlineCustomerServiceCenter.operator.entity.Operator;
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
    private SolutionService solutionService;
    @Autowired
    private IssueService issueService;


    @Override
    public String loginOperator(String email, String password) {

        Optional<OperatorLoginDto> foundOperator= this.operatorRespository.findOperatorByEmail(email);
        if(foundOperator.isPresent()){
                if(foundOperator.get().getPassword().equals(password)){
                    return "Login Successfull";
                }else{
                    return "Password is Wrong";
                }

        }else {
            return "Account does not exits";
        }
    }



    @Override
    public Operator updateOperatorProfile(Operator updatedoperator) {
        return this.operatorRespository.save(updatedoperator);
    }
    @Override
    public Issue addIssueSolution(Integer issueId, String solutionDescription) throws SolutionException, IssueNotFoundException {
         Issue issue=  this.issueService.getIssueById(issueId);
         Solution solution= solutionService.createSolution(solutionDescription);
        issueService.addSolutionToIssueById(issueId,solution);
        return this.issueService.getIssueById(issueId);
    }

}
