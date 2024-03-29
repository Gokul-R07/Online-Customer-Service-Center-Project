package com.onlineCustomerServiceCenter.operator.controller;

import com.onlineCustomerServiceCenter.issue.entity.Issue;
import com.onlineCustomerServiceCenter.issue.exception.IssueNotFoundException;
import com.onlineCustomerServiceCenter.issue.exception.NullIssueException;
import com.onlineCustomerServiceCenter.operator.dto.OperatorDetailsDto;
import com.onlineCustomerServiceCenter.operator.dto.PasswordDto;
import com.onlineCustomerServiceCenter.operator.dto.OperatorLoginDto;
import com.onlineCustomerServiceCenter.operator.entity.Operator;
import com.onlineCustomerServiceCenter.operator.exceptions.IncorrectPasswordException;
import com.onlineCustomerServiceCenter.operator.exceptions.NullException;
import com.onlineCustomerServiceCenter.operator.exceptions.OperatorNotFoundException;
import com.onlineCustomerServiceCenter.operator.exceptions.*;
import com.onlineCustomerServiceCenter.operator.service.OperatorService;
import com.onlineCustomerServiceCenter.operator.dto.IssueSolutionDto;
import com.onlineCustomerServiceCenter.solution.exceptions.SolutionException;
import jakarta.validation.Valid;

import java.util.Collections;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
@RestController
@Slf4j
public class OperatorController {
    @Autowired
    private OperatorService operatorService;
    @PostMapping("operator/login")
    public Operator loginOperator(@RequestBody OperatorLoginDto operatorLoginDto) throws OperatorNotFoundException, IncorrectPasswordException, NullException {
        return this.operatorService.loginOperator(operatorLoginDto.getEmail(), operatorLoginDto.getPassword());
    }
    @PatchMapping("operator/change-password")
    public String changePassword(@RequestBody PasswordDto passwordDto) throws OperatorNotFoundException, IncorrectPasswordException, NullException {
                return this.operatorService.changePassword(passwordDto.getOperatorId(),passwordDto.getOldPassword(),passwordDto.getNewPassword());
    }

    @PostMapping("operator/solution")
    public String addIssueSolution(@RequestBody IssueSolutionDto issueSolutionDto) throws IssueNotFoundException, NullException {
        return this.operatorService.addIssueSolution(issueSolutionDto.getIssueId(), issueSolutionDto.getSolutionDescription(), issueSolutionDto.getOperatorId());
    }
  @GetMapping("operator")
    public Operator getOperatorDetailsById(@RequestParam Integer operatorId) {
        return this.operatorService.getOperatorDetailsById(operatorId);
    }




    @GetMapping("/pending-issue-by-id")
    public List<Issue> getAllPendingIssue(@RequestParam Integer operatorid){
        try{
            return operatorService.getAllPendingIssueByOperatorId(operatorid);

        }
        catch(PendingIssueExp e){
            log.error("Error Occured at : getAllPendingIssue"+e);
        }

        return Collections.emptyList();

    }

    @GetMapping("/allocated-issue-by-id/{operatorid}")
    public List<Issue> getAllAllocatedIssueById(@PathVariable Integer operatorid) {
        try {
            return operatorService.getAllAllocatedIssueByOperatorId(operatorid);
        } catch (AllocatedIssueExp e) {
            log.error("Error Occurred at getAllAllocatedIssueById: " + e.getMessage());
        }
        return Collections.emptyList();
    }

    @GetMapping("/Allocated-issue")
    public List<Issue> getAllAllocatedIssue() {
        try {
            return this.operatorService.getAllAllocatedIssue();
        }
        catch (AllocatedIssueExp e){
            log.error("Error Occurred at : getAllAllocatedIssue" + e);
        }
        return Collections.emptyList();
    }

    @GetMapping("/Allocated-issue-count")
    public Long getAllAllocatedIssueCount(){
        try {
            return this.operatorService.getAllAllocatedIssueCount();
        }
        catch (AllocatedIssueExp e){
            log.error("Erro occured at : getAllAllocatedIssueCount"+e);

        }
        return null;
    }

    @GetMapping("/Pending-issue")
    public List<Issue> getAllPendingIssue(){
        try {
            return this.operatorService.getAllPendingIssue();
        }
        catch(PendingIssueExp e){
            log.error("Error Occurred at getAllPendingIssue"+e);
        }
        return Collections.emptyList();
    }

    @GetMapping("/Pending-issue-count")
    public Long getAllPendingIssueCount(){
        try {
            return this.operatorService.getAllPendingIssueCount();
        }
        catch (PendingIssueExp e){
            log.error("Error Occured at : getAllPendingIssueCount"+e);
        }
        return null;
    }


}
