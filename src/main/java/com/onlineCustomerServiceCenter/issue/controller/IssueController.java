package com.onlineCustomerServiceCenter.issue.controller;

import com.onlineCustomerServiceCenter.customer.entity.Customer;
import com.onlineCustomerServiceCenter.customer.exceptions.CustomerRegisterException;
import com.onlineCustomerServiceCenter.customer.service.CustomerService;
import com.onlineCustomerServiceCenter.issue.entity.Issue;
import com.onlineCustomerServiceCenter.issue.exception.IssueNotFoundException;
import com.onlineCustomerServiceCenter.solution.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IssueController {
    //No endpoints for Issue module. It is to be linked with other modules


    @Autowired
    private CustomerService customerService;

    @PostMapping("issue")
    public Customer addIssueToCustomer(@RequestBody Integer customerId,@RequestBody Issue newIssue ) throws CustomerRegisterException {
        return this.customerService.addIssueToCustomer(customerId, newIssue);
    }


}
