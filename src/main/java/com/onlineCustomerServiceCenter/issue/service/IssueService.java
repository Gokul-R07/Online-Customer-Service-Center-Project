package com.onlineCustomerServiceCenter.issue.service;

import com.onlineCustomerServiceCenter.customer.entity.Customer;
import com.onlineCustomerServiceCenter.customer.exceptions.CustomerRegisterException;
import com.onlineCustomerServiceCenter.issue.entity.Issue;
import com.onlineCustomerServiceCenter.issue.exception.IssueNotFoundException;
import com.onlineCustomerServiceCenter.issue.exception.NullIssueException;
import com.onlineCustomerServiceCenter.solution.entity.Solution;

import java.util.List;

public interface IssueService {
    public Issue addIssue(Issue issue) throws NullIssueException;
    public Issue updateIssueDescById(Issue issue, String newDesc) throws NullIssueException;

    public Customer deleteIssueById(Integer customerId,Integer id) throws IssueNotFoundException;
    public Issue getIssueById(Integer id) throws IssueNotFoundException;
    public List<Issue> getAllIssues();

    public List<Issue> getAllIssuesByType(String type);

    public String addSolutionToIssueById(Integer issueId, Solution solution) throws IssueNotFoundException;

    Customer updateIssueDescById(Integer customerId, Integer issueId, Issue issue) throws CustomerRegisterException, IssueNotFoundException;

    Customer addIssueToCustomer(Integer customerId, Issue newIssue) throws CustomerRegisterException;
}
