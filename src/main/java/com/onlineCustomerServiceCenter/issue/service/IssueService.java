package com.onlineCustomerServiceCenter.issue.service;

import com.onlineCustomerServiceCenter.customer.entity.Customer;
import com.onlineCustomerServiceCenter.customer.exceptions.CustomerNotFoundException;
import com.onlineCustomerServiceCenter.issue.entity.Issue;
import com.onlineCustomerServiceCenter.issue.exception.IssueNotFoundException;
import com.onlineCustomerServiceCenter.issue.exception.NullIssueException;

import java.util.List;

public interface IssueService {

    public Issue getIssueById(Integer id) throws IssueNotFoundException;
    public List<Issue> getAllIssues() throws NullIssueException;

    public List<Issue> getAllIssuesByType(String type);

    Issue updateIssueDescriptionById(Integer customerId, Integer issueId, String newDesc) throws CustomerNotFoundException, IssueNotFoundException;

   
  
    Customer addIssueToCustomer(Integer customerId, Issue newIssue) throws CustomerNotFoundException;

    List<Issue> getAllIssuesByCustomerId(Integer customerId) throws CustomerNotFoundException;

    Issue deleteIssueFromCustomer(Integer customerId, Integer issueId) throws CustomerNotFoundException, IssueNotFoundException;

    Issue getIssueByIssueId(Integer issueId) throws IssueNotFoundException;
}
