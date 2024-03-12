package com.onlineCustomerServiceCenter.operator.service;

import com.onlineCustomerServiceCenter.issue.entity.Issue;
import com.onlineCustomerServiceCenter.operator.entity.Operator;
import com.onlineCustomerServiceCenter.issue.exception.IssueNotFoundException;
import com.onlineCustomerServiceCenter.solution.exceptions.SolutionException;

public interface OperatorService {

    Operator updateOperatorProfile(Operator updatedoperator);

    String loginOperator(String email, String password);

    Issue addIssueSolution(Integer issueId, String solutionDescription) throws SolutionException, IssueNotFoundException;
}
