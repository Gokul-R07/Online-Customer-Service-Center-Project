package com.onlineCustomerServiceCenter.operator.service;

import com.onlineCustomerServiceCenter.issue.exception.NullIssueException;

import java.util.List;

import com.onlineCustomerServiceCenter.issue.entity.Issue;



import com.onlineCustomerServiceCenter.issue.exception.IssueNotFoundException;
import com.onlineCustomerServiceCenter.operator.entity.Operator;
import com.onlineCustomerServiceCenter.operator.exceptions.*;
import com.onlineCustomerServiceCenter.solution.exceptions.SolutionException;

public interface OperatorService {
    String loginOperator(String email, String password) throws OperatorNotFoundException, IncorrectPasswordException, NullException;

<<<<<<< HEAD
    String loginOperator(String email, String password);

    static Operator updateOperatorProfile(Operator updatedoperator) {
        return null;
    }
=======
    String changePassword(String email, String oldPassword,String newPassword) throws  OperatorNotFoundException, IncorrectPasswordException, NullException;
>>>>>>> eb9e82671abeb4beb2fd08a44d53e7e22e8439c8


<<<<<<< HEAD
    Operator updateOperatorProfile(Operator updatedoperator);

    Issue addIssueSolution(Integer issueId, String solutionDescription) throws SolutionException, IssueNotFoundException;
=======
    String addIssueSolution(Integer issueId, String solutionDescription,Integer operatorId) throws  IssueNotFoundException, NullException;

    List<Issue> getAllAllocatedIssue()throws AllocatedIssueExp;

    Long getAllAllocatedIssueCount() throws AllocatedIssueExp;

    List<Issue> getAllPendingIssueByOperatorId(Integer operatorid) throws PendingIssueExp;

    List<Issue> getAllAllocatedIssueByOperatorId(Integer operatorid) throws AllocatedIssueExp;

    List<Issue> getAllPendingIssue() throws PendingIssueExp;

    Long getAllPendingIssueCount() throws PendingIssueExp;


    Operator addOperator(Operator newOperator);

    Operator updateOperator(Operator operator);

    Operator deleteOperatorById(Long id);
}
