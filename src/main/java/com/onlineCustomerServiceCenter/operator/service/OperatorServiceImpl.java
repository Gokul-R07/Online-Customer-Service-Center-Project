package com.onlineCustomerServiceCenter.operator.service;

import com.onlineCustomerServiceCenter.issue.entity.Issue;
import com.onlineCustomerServiceCenter.issue.dao.IssueRepository;
import com.onlineCustomerServiceCenter.issue.entity.IssueStatus;
import com.onlineCustomerServiceCenter.issue.service.IssueService;
import com.onlineCustomerServiceCenter.issue.exception.IssueNotFoundException;
import com.onlineCustomerServiceCenter.operator.dao.OperatorRepository;
import com.onlineCustomerServiceCenter.operator.dto.OperatorDetailsDto;
import com.onlineCustomerServiceCenter.operator.dto.OperatorLoginDto;
import com.onlineCustomerServiceCenter.operator.entity.Operator;
import com.onlineCustomerServiceCenter.operator.exceptions.AllocatedIssueExp;
import com.onlineCustomerServiceCenter.operator.exceptions.IncorrectPasswordException;
import com.onlineCustomerServiceCenter.operator.exceptions.NullException;
import com.onlineCustomerServiceCenter.operator.exceptions.OperatorNotFoundException;
import com.onlineCustomerServiceCenter.solution.dao.SolutionRepository;
import com.onlineCustomerServiceCenter.solution.entity.Solution;
import com.onlineCustomerServiceCenter.solution.service.SolutionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
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
    public Operator loginOperator(String email, String password) throws OperatorNotFoundException, IncorrectPasswordException, NullException {
        if(email==null ){
            throw new NullException("Email cannot be null");
        } else if (password==null) {
            throw new NullException("Password cannot be null");
        }

        Optional<Operator> operatorOptional = operatorRepository.findOperatorByEmail(email);
        if (operatorOptional.isPresent()) {
            Operator operator = operatorOptional.get();
            if (operator.getPassword().equals(password)) {
                return operator;
            } else {
                throw new IncorrectPasswordException("Incorrect password");
            }
        } else {
            throw new OperatorNotFoundException("Operator not found");
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
        Optional<Operator> operatorOptional=this.operatorRepository.findById(operatorId);
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
            throw new OperatorNotFoundException("Given Operator does not exists");
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
             issue.setIssueStatus(IssueStatus.SOLVED);
             this.issueRepository.save(issue);

             return "Solution saved successfully";
         }else{
             throw new IssueNotFoundException("No Issue found with given issue id:"+ issueId);
         }


    }

    @Override
    public Operator getOperatorDetailsById(Integer operatorId) {
        return this.operatorRepository.findById(operatorId).get();
    }

    @Override
    public List<Issue> getAllAllocatedIssue()throws AllocatedIssueExp {
        List<Issue> issues = this.issueRepository.findAll();
        if(issues.isEmpty()){
            throw new AllocatedIssueExp("there are no allocated issues");
        }
        return this.issueRepository.findAll();

    }

    @Override
    public Long getAllAllocatedIssueCount() throws AllocatedIssueExp{
        List<Issue> issues = this.issueRepository.findAll();
        if(issues.isEmpty()){
            throw new AllocatedIssueExp("Issues are not present");
        }
        return this.issueRepository.count();
    }


    @Override
    public List<Issue> getAllPendingIssueByOperatorId(Integer operatorid) {
        Optional<Operator> operatorIdOptional= this.operatorRepository.findById(operatorid);
        log.info(String.valueOf(operatorIdOptional));
        List<Issue> pendIssues = new ArrayList<>();
        if(operatorIdOptional.isEmpty()) {
            return Collections.emptyList();

        }
        else{
            for(Issue issue: operatorIdOptional.get().getCustomerIssues()){
                if(issue.getIssueStatus().equals("pending")){
                    pendIssues.add(issue);
                }

            }
        }
        return pendIssues;
    }

    @Override
    public List<Issue> getAllAllocatedIssueByOperatorId(Integer operatorid) {
        Optional<Operator> operator = this.operatorRepository.findById(operatorid);
        List<Issue> allocatedIssue = new ArrayList<>();
        if (operator.isEmpty()) {
            return Collections.emptyList();
        } else {
            for (Issue issue : operator.get().getCustomerIssues()) {
                if(issue.getIssueStatus()==IssueStatus.INPROGRESS){
                    allocatedIssue.add(issue);
                }
            }
        }
        return allocatedIssue;
    }


    @Override
    public List<Issue> getAllPendingIssue() {
        List<Issue> issues = this.issueRepository.findAll();
        List<Issue> pendingIssue = new ArrayList<>();

        for(Issue issue : issues){
            if(issue.getIssueStatus().equals("pending")){
                pendingIssue.add(issue);
            }
        }
        if(pendingIssue.isEmpty()){
            return Collections.emptyList();
        }
        else{
            return pendingIssue;
        }
    }

    @Override
    public Long getAllPendingIssueCount() {
        List<Issue> issueCounter = getAllPendingIssue();
        if(issueCounter.isEmpty()){
            return 0L;
        }
        else{
            return issueCounter.stream().count();
        }

    }



}
