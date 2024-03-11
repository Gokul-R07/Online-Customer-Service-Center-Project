package com.onlineCustomerServiceCenter.issue.service;

import com.onlineCustomerServiceCenter.customer.entity.Customer;
import com.onlineCustomerServiceCenter.customer.exceptions.CustomerNotFoundException;
import com.onlineCustomerServiceCenter.issue.entity.Issue;
import com.onlineCustomerServiceCenter.issue.exception.IssueNotFoundException;
import com.onlineCustomerServiceCenter.solution.entity.Solution;

import java.util.List;

public interface IssueService {


    public Customer deleteIssueById(Integer customerId,Integer id) throws IssueNotFoundException;

    public Issue getIssueById(Integer id) throws IssueNotFoundException;
    public List<Issue> getAllIssues();

    public List<Issue> getAllIssuesByType(String type);

    public void addSolutionToIssueById(Integer issueId, Solution solution) throws IssueNotFoundException;


    Issue updateIssueDescriptionById(Integer customerId, Integer issueId, String newDesc) throws CustomerNotFoundException, IssueNotFoundException;

    Customer updateIssueDescById(Integer customerId, Integer issueId, Issue issue) throws CustomerRegisterException, IssueNotFoundException;


    Customer addIssueToCustomer(Integer customerId, Issue newIssue) throws CustomerNotFoundException;

    List<Issue> getAllIssuesByCustomerId(Integer customerId) throws CustomerNotFoundException;

    String deleteIssueFromCustomer(Integer customerId, Integer issueId) throws CustomerNotFoundException, IssueNotFoundException;
}
