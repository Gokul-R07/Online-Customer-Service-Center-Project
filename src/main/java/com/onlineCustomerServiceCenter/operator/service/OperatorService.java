package com.onlineCustomerServiceCenter.operator.service;

import com.onlineCustomerServiceCenter.admin.exceptions.SolutionException;
import com.onlineCustomerServiceCenter.issue.entity.Issue;
import com.onlineCustomerServiceCenter.issue.exception.IssueNotFoundException;
import com.onlineCustomerServiceCenter.operator.entity.Operator;
import com.onlineCustomerServiceCenter.operator.exceptions.*;

import java.util.List;

public interface OperatorService {
    String loginOperator(String email, String password) throws OperatorNotFoundException, IncorrectPasswordException, NullException;





    String changePassword(String email, String oldPassword,String newPassword) throws  OperatorNotFoundException, IncorrectPasswordException, NullException;



    Operator updateOperatorProfile(Operator updatedoperator);

   Issue addIssueSolution(Integer issueId, String solutionDescription) throws SolutionException, IssueNotFoundException;
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
