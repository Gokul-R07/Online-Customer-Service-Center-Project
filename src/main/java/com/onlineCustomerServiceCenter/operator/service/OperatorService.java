package com.onlineCustomerServiceCenter.operator.service;

import com.onlineCustomerServiceCenter.issue.Issue;
import com.onlineCustomerServiceCenter.operator.entity.Operator;
import com.onlineCustomerServiceCenter.issue.exception.IssueNotFoundException;
import com.onlineCustomerServiceCenter.solution.exceptions.SolutionException;

public interface OperatorService {

    static Operator updateOperatorProfile(Operator updatedoperator) {
        return null;
    }

    static String loginOperator(String email, String password);

    Issue addIssueSolution(Integer issueId, String solutionDescription) throws SolutionException, IssueNotFoundException;

    static boolean deleteOperator(Integer optId) {
        return false;
    }
}
