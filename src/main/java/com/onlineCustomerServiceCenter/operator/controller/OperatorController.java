package com.onlineCustomerServiceCenter.operator.controller;

import com.onlineCustomerServiceCenter.issue.Issue;
import com.onlineCustomerServiceCenter.operator.entity.Operator;
import com.onlineCustomerServiceCenter.operator.dto.OperatorLoginDto;
import com.onlineCustomerServiceCenter.operator.service.OperatorService;
import com.onlineCustomerServiceCenter.operator.dto.IssueSolutionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OperatorController {
    @Autowired
    private OperatorService operatorService;
    @GetMapping("operator")
    public String loginOperator(@RequestParam OperatorLoginDto operatorLoginDto){
        return this.operatorService.loginOperator(operatorLoginDto.getEmail(),operatorLoginDto.getPassword());
    }
    @PutMapping("operator")
    public Operator updateOperatorProfile(@RequestBody Operator updatedoperator){
        return this.operatorService.updateOperatorProfile(updatedoperator);
    }
    @PostMapping("operator/solution")
    public Issue addIssueSolution(@RequestBody IssueSolutionDto issueSolutionDto){
        return this.operatorService.addIssueSolution(issueSolutionDto.getIssueId(),issueSolutionDto.getSolutionDescription());
    }

}