package com.onlineCustomerServiceCenter.issue.controller;

import com.onlineCustomerServiceCenter.customer.dao.CustomerRepository;
import com.onlineCustomerServiceCenter.customer.entity.Customer;
import com.onlineCustomerServiceCenter.customer.exceptions.CustomerNotFoundException;
import com.onlineCustomerServiceCenter.customer.exceptions.CustomerRegisterException;
import com.onlineCustomerServiceCenter.customer.service.CustomerService;
import com.onlineCustomerServiceCenter.issue.dao.IssueRepository;
import com.onlineCustomerServiceCenter.issue.entity.Issue;
import com.onlineCustomerServiceCenter.issue.exception.IssueNotFoundException;
import com.onlineCustomerServiceCenter.issue.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IssueController {

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
    public Customer addIssueToCustomer(@PathVariable Integer customerId,@RequestBody Issue newIssue ) throws CustomerNotFoundException {
        return this.issueService.addIssueToCustomer(customerId, newIssue);
    }

    @PutMapping("issue/update/{customerId}/{issueId}")
    public Issue updateIssueById(@PathVariable Integer customerId, @PathVariable Integer issueId, @RequestBody String newDesc) throws IssueNotFoundException, CustomerNotFoundException {
        return this.issueService.updateIssueDescriptionById(customerId, issueId, newDesc);


    }

    @DeleteMapping("/{customerId}/issues/{issueId}")
    public String deleteIssueById(@PathVariable Integer customerId, @PathVariable Integer issueId) throws Exception {
        return issueService.deleteIssueFromCustomer(customerId, issueId);
    }

    @GetMapping("issues/{customerId}")
    public List<Issue> getAllIssueByCustomerId(@PathVariable Integer customerId) throws CustomerNotFoundException {
        return this.issueService.getAllIssuesByCustomerId(customerId);
    }

    @GetMapping("issues")
    public List<Issue> getAllIssue(){
        return this.issueService.getAllIssues();
    }


}
