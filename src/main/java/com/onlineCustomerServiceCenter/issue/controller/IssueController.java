package com.onlineCustomerServiceCenter.issue.controller;

import com.onlineCustomerServiceCenter.customer.dao.CustomerRepository;
import com.onlineCustomerServiceCenter.customer.entity.Customer;
import com.onlineCustomerServiceCenter.customer.exceptions.CustomerRegisterException;
import com.onlineCustomerServiceCenter.customer.service.CustomerService;
import com.onlineCustomerServiceCenter.issue.dao.IssueRepository;
import com.onlineCustomerServiceCenter.issue.entity.Issue;
import com.onlineCustomerServiceCenter.issue.exception.IssueNotFoundException;
import com.onlineCustomerServiceCenter.issue.service.IssueService;
import com.onlineCustomerServiceCenter.solution.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class IssueController {
    //No endpoints for Issue module. It is to be linked with other modules


    @Autowired
    private CustomerService customerService;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private IssueService issueService
;

    @PostMapping("issue/{customerId}")
    public Customer addIssueToCustomer(@RequestParam Integer customerId,@RequestBody Issue newIssue ) throws CustomerRegisterException {
        return this.issueService.addIssueToCustomer(customerId, newIssue);
    }

    @PatchMapping("issue/update/{customerId}/{issueId}")
    public Customer updateIssueById(@PathVariable Integer customerId, @PathVariable Integer issueId, @RequestBody String newDesc) throws IssueNotFoundException, CustomerRegisterException{
        return this.issueService.updateIssueDescById(customerId, issueId, newDesc);

    }

    @PutMapping("issue/delete/{customerId}/{issueId}")
    public void deleteIssueById(@PathVariable Integer customerId, @PathVariable Integer issueId) throws IssueNotFoundException {
        this.issueService.deleteIssueById(issueId);

    }


}




