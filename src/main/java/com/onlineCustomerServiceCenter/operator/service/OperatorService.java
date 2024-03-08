package com.onlineCustomerServiceCenter.operator.service;

import com.onlineCustomerServiceCenter.issue.exception.NullIssueException;

import java.util.List;

import com.onlineCustomerServiceCenter.issue.Issue;
import com.onlineCustomerServiceCenter.issue.exception.IssueNotFoundException;
import com.onlineCustomerServiceCenter.operator.exceptions.*;
import com.onlineCustomerServiceCenter.solution.exceptions.SolutionException;

public interface OperatorService {
    String loginOperator(String email, String password) throws OperatorNotFoundException, IncorrectPasswordException, NullException;

    String changePassword(String email, String oldPassword,String newPassword) throws  OperatorNotFoundException, IncorrectPasswordException, NullException;



    String addIssueSolution(Integer issueId, String solutionDescription,Integer operatorId) throws  IssueNotFoundException, NullException;

    List<Issue> getAllAllocatedIssue()throws AllocatedIssueExp;

    Long getAllAllocatedIssueCount() throws AllocatedIssueExp;

    List<Issue> getAllPendingIssueByOperatorId(Integer operatorid) throws PendingIssueExp;

    List<Issue> getAllAllocatedIssueByOperatorId(Integer operatorid) throws AllocatedIssueExp;

    List<Issue> getAllPendingIssue() throws PendingIssueExp;

    Long getAllPendingIssueCount() throws PendingIssueExp;
}
