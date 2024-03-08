package com.onlineCustomerServiceCenter.operator.controller;

import com.onlineCustomerServiceCenter.issue.Issue;
import com.onlineCustomerServiceCenter.issue.exception.IssueNotFoundException;
import com.onlineCustomerServiceCenter.issue.exception.NullIssueException;
import com.onlineCustomerServiceCenter.operator.dto.PasswordDto;
import com.onlineCustomerServiceCenter.operator.dto.OperatorLoginDto;
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

@RestController
@Slf4j
public class OperatorController {
    @Autowired
    private OperatorService operatorService;
    @PostMapping("operator/login")
    public String loginOperator(@RequestBody OperatorLoginDto operatorLoginDto){
        try {
            return this.operatorService.loginOperator(operatorLoginDto.getEmail(),operatorLoginDto.getPassword());
        } catch (OperatorNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IncorrectPasswordException e) {
            throw new RuntimeException(e);
        } catch (NullException e) {
            throw new RuntimeException(e);
        }
    }
    @PatchMapping("operator")
    public String changePassword(@RequestBody PasswordDto passwordDto){
            try {
                return this.operatorService.changePassword(passwordDto.getEmail(),passwordDto.getOldPassword(),passwordDto.getNewPassword());

            } catch (OperatorNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IncorrectPasswordException e) {
                throw new RuntimeException(e);
            } catch (NullException e) {
                throw new RuntimeException(e);
            }

    }
    @PostMapping("operator/solution")
    public String addIssueSolution(@RequestBody IssueSolutionDto issueSolutionDto){
        try {
            return this.operatorService.addIssueSolution(issueSolutionDto.getIssueId(),issueSolutionDto.getSolutionDescription(),issueSolutionDto.getOperatorId());
        } catch (IssueNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NullException e) {
            throw new RuntimeException(e);
        }
    }

   
    @GetMapping("/pending-issue-by-id")
    public List<Issue> getAllPendingIssue(@RequestBody Integer operatorid){
        try{
            return operatorService.getAllPendingIssueByOperatorId(operatorid);

        }
        catch(PendingIssueExp e){
            log.error("Error Occured at : getAllPendingIssue"+e);
        }

        return Collections.emptyList();

    }

    @GetMapping("/Allocated-issue-by-id")
    public List<Issue> getAllAllocatedIssueById(@RequestBody Integer operatorid){
        try{
            return operatorService.getAllAllocatedIssueByOperatorId(operatorid);
        }
        catch (AllocatedIssueExp e){
            log.error("Error Occured at getAllAllocatedIssueById "+ e);
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
