package com.onlineCustomerServiceCenter.operator.service;

import java.util.List;
import com.onlineCustomerServiceCenter.issue.entity.Issue;
import com.onlineCustomerServiceCenter.issue.exception.IssueNotFoundException;
import com.onlineCustomerServiceCenter.operator.dto.OperatorDetailsDto;
import com.onlineCustomerServiceCenter.operator.entity.Operator;
import com.onlineCustomerServiceCenter.operator.exceptions.IncorrectPasswordException;
import com.onlineCustomerServiceCenter.operator.exceptions.NullException;
import com.onlineCustomerServiceCenter.operator.exceptions.OperatorNotFoundException;
import com.onlineCustomerServiceCenter.operator.exceptions.*;

public interface OperatorService {
    Operator loginOperator(String email, String password) throws OperatorNotFoundException, IncorrectPasswordException, NullException;

    String changePassword(Integer operatorId, String oldPassword,String newPassword) throws  OperatorNotFoundException, IncorrectPasswordException, NullException;


    String addIssueSolution(Integer issueId, String solutionDescription,Integer operatorId) throws  IssueNotFoundException, NullException;


    Operator getOperatorDetailsById(Integer operatorId);

    List<Issue> getAllAllocatedIssue()throws AllocatedIssueExp;

    Long getAllAllocatedIssueCount() throws AllocatedIssueExp;

    List<Issue> getAllPendingIssueByOperatorId(Integer operatorid) throws PendingIssueExp;

    List<Issue> getAllAllocatedIssueByOperatorId(Integer operatorid) throws AllocatedIssueExp;

    List<Issue> getAllPendingIssue() throws PendingIssueExp;

    Long getAllPendingIssueCount() throws PendingIssueExp;

}
