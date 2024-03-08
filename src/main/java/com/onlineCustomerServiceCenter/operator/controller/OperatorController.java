package com.onlineCustomerServiceCenter.operator.controller;

import com.onlineCustomerServiceCenter.issue.entity.Issue;
import com.onlineCustomerServiceCenter.issue.exception.IssueNotFoundException;
import com.onlineCustomerServiceCenter.issue.exception.NullIssueException;
import com.onlineCustomerServiceCenter.operator.dto.PasswordDto;
import com.onlineCustomerServiceCenter.operator.dto.OperatorLoginDto;
import com.onlineCustomerServiceCenter.operator.exceptions.IncorrectPasswordException;
import com.onlineCustomerServiceCenter.operator.exceptions.NullException;
import com.onlineCustomerServiceCenter.operator.exceptions.OperatorNotFoundException;
import com.onlineCustomerServiceCenter.operator.service.OperatorService;
import com.onlineCustomerServiceCenter.operator.dto.IssueSolutionDto;
import com.onlineCustomerServiceCenter.solution.exceptions.SolutionException;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
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
        return operatorService.getAllPendingIssueByOperatorId(operatorid);
    }

    @GetMapping("Allocated-issue-by-id")
    public List<Issue> getAllAllocatedIssueById(@RequestBody Integer operatorid){
        return operatorService.getAllAllocatedIssueByOperatorId(operatorid);
    }

}
