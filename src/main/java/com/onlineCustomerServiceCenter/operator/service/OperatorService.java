package com.onlineCustomerServiceCenter.operator.service;

import com.onlineCustomerServiceCenter.issue.exception.NullIssueException;

import java.util.List;

import com.onlineCustomerServiceCenter.issue.Issue;
import com.onlineCustomerServiceCenter.issue.exception.IssueNotFoundException;
import com.onlineCustomerServiceCenter.operator.exceptions.IncorrectPasswordException;
import com.onlineCustomerServiceCenter.operator.exceptions.NullException;
import com.onlineCustomerServiceCenter.operator.exceptions.OperatorNotFoundException;
import com.onlineCustomerServiceCenter.solution.exceptions.SolutionException;

public interface OperatorService {
    String loginOperator(String email, String password) throws OperatorNotFoundException, IncorrectPasswordException, NullException;

    String changePassword(String email, String oldPassword,String newPassword) throws  OperatorNotFoundException, IncorrectPasswordException, NullException;



    String addIssueSolution(Integer issueId, String solutionDescription,Integer operatorId) throws  IssueNotFoundException, NullException;

    Issue getAllAllocatedIssue();

    Long getAllAllocatedIssueCount();

    List<Issue> getAllPendingIssueByOperatorId(Integer operatorid);

    List<Issue> getAllAllocatedIssuByOperatorId(Integer operatorid);
}
